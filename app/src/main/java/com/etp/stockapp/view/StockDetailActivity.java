package com.etp.stockapp.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etp.stockapp.R;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.view_model.StockDetailViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class StockDetailActivity extends BaseActivity {

    public static void startActivity(Context context, StockDetail stockDetail) {

        Intent intent = new Intent();
        intent.setClass(context, StockDetailActivity.class);
        intent.putExtra("gson", new Gson().toJson(stockDetail));
        context.startActivity(intent);
    }

    private StockDetailViewModel mStockDetailViewModel;

    private TextView mStockIDTextView;
    private TextView mStockNameTextView;
    private TextView mOpenPrizeTextView;
    private TextView mClosePrizeTextView;
    private TextView mHighestPrizeTextView;
    private TextView mLowestPrizeTextView;
    private TextView mRangeTextView;
    private TextView mDealStockTextView;
    private RecyclerView mRecyclerView;
    private TextView mRevenueReleaseYearTextView;
    private TextView mRevenueReleaseMonthTextView;
    private TextView mRevenueReleaseDayTextView;
    private TextView mRevenueDataYearTextView;
    private TextView mRevenueDataMonthTextView;
    private TextView mRevenueMonthPercentTextView;
    private TextView mRevenueYearPercentTextView;

    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        //region View
        mStockIDTextView = findViewById(R.id.stock_id_text_view);
        mStockNameTextView = findViewById(R.id.stock_name_text_view);
        mOpenPrizeTextView = findViewById(R.id.open_prize_text_view);
        mClosePrizeTextView = findViewById(R.id.close_prize_text_view);
        mHighestPrizeTextView = findViewById(R.id.highest_prize_text_view);
        mLowestPrizeTextView = findViewById(R.id.lowest_prize_text_view);
        mRangeTextView = findViewById(R.id.range_text_view);
        mDealStockTextView = findViewById(R.id.deal_stock_text_view);
        mRecyclerView = findViewById(R.id.corporation_recycler_view);
        mRevenueReleaseYearTextView = findViewById(R.id.release_year_text_view);
        mRevenueReleaseMonthTextView = findViewById(R.id.release_month_text_view);
        mRevenueReleaseDayTextView = findViewById(R.id.release_day_text_view);
        mRevenueDataYearTextView = findViewById(R.id.data_year_text_view);
        mRevenueDataMonthTextView = findViewById(R.id.data_month_text_view);
        mRevenueMonthPercentTextView = findViewById(R.id.month_compare_percent_text_view);
        mRevenueYearPercentTextView = findViewById(R.id.year_compare_percent_text_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //endregion

        //region Data
        mStockDetailViewModel = new ViewModelProvider(this).get(StockDetailViewModel.class);
        mAdapter = new RecyclerViewAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        //endregion

        if (mStockDetailViewModel.isSetUp(this.getIntent().getStringExtra("gson"))) {

            subscribeSubject();
        }
    }

    private void subscribeSubject() {

        //region StockRangeInfoDetail 相關View給值
        {
            Disposable disposable = mStockDetailViewModel.output.stockRangeInfoSubject.subscribe(new Consumer<StockRangeInfoDetail>() {
                @Override
                public void accept(StockRangeInfoDetail infoDetail) throws Exception {

                    mStockIDTextView.setText(infoDetail.getStockID());
                    mStockNameTextView.setText(infoDetail.getStockName());
                    mOpenPrizeTextView.setText(infoDetail.getOpenPrize());
                    mClosePrizeTextView.setText(infoDetail.getClosePrize());
                    mHighestPrizeTextView.setText(infoDetail.getHighestPrize());
                    mLowestPrizeTextView.setText(infoDetail.getLowestPrize());
                    mRangeTextView.setText(infoDetail.getRange());
                    mDealStockTextView.setText(infoDetail.getDealStock());
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("///", "stockRangeInfoSubject error: " + throwable);
                }
            });
            addDisposable(disposable);
        }
        //endregion

        //region OperatingRevenueDetail 相關View給值
        {
            Disposable disposable = mStockDetailViewModel.output.operatingRevenueSubject.subscribe(new Consumer<OperatingRevenueDetail>() {
                @Override
                public void accept(OperatingRevenueDetail detail) throws Exception {

                    mRevenueReleaseYearTextView.setText(detail.getReleaseDate().substring(0, 3));
                    mRevenueReleaseMonthTextView.setText(detail.getReleaseDate().substring(3, 5));
                    mRevenueReleaseDayTextView.setText(detail.getReleaseDate().substring(5));
                    mRevenueDataYearTextView.setText(detail.getDataDate().substring(0, 3));
                    mRevenueDataMonthTextView.setText(detail.getDataDate().substring(3));
                    mRevenueMonthPercentTextView.setText(detail.getCompareWithLastMonthRevenuePercent().substring(0, 6));
                    mRevenueYearPercentTextView.setText(detail.getCompareWithLastYearRevenuePercent().substring(0, 6));
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("///", "operatingRevenueSubject error: " + throwable);
                }
            });
            addDisposable(disposable);
        }
        //endregion

        //region CorporationDetailList 相關View給值
        {
            Disposable disposable = mStockDetailViewModel.output.corporationListSubject.subscribe(new Consumer<List<CorporationDetail>>() {
                @Override
                public void accept(List<CorporationDetail> corporationDetailList) throws Exception {

                    mAdapter.setCorporationDetailList(corporationDetailList);
                    mAdapter.notifyDataSetChanged();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("///", "corporationListSubject error: " + throwable);
                }
            });
            addDisposable(disposable);
        }
        //endregion
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {

        private List<CorporationDetail> mCorporationDetailList;

        public RecyclerViewAdapter(List<CorporationDetail> corporationDetailList) {
            mCorporationDetailList = corporationDetailList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_corporation, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.setItem(getItem(position));
        }

        @Override
        public int getItemCount() {
            return mCorporationDetailList.size();
        }

        public CorporationDetail getItem(int position) {
            return mCorporationDetailList.get(position);
        }

        public List<CorporationDetail> getCorporationDetailList() {
            return mCorporationDetailList;
        }

        public void setCorporationDetailList(List<CorporationDetail> corporationDetailList) {
            mCorporationDetailList = corporationDetailList;
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mDateTextView;
        private TextView mForeignBuyTextView;
        private TextView mForeignSellTextView;
        private TextView mForeignOverTextView;
        private TextView mInvestmentBuyTextView;
        private TextView mInvestmentSellTextView;
        private TextView mInvestmentOverTextView;
        private TextView mSelfBuyTextView;
        private TextView mSelfSellTextView;
        private TextView mSelfOverTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
            mForeignBuyTextView = itemView.findViewById(R.id.foreign_buy_text_view);
            mForeignSellTextView = itemView.findViewById(R.id.foreign_sell_text_view);
            mForeignOverTextView = itemView.findViewById(R.id.foreign_over_text_view);
            mInvestmentBuyTextView = itemView.findViewById(R.id.investment_buy_text_view);
            mInvestmentSellTextView = itemView.findViewById(R.id.investment_sell_text_view);
            mInvestmentOverTextView = itemView.findViewById(R.id.investment_over_text_view);
            mSelfBuyTextView = itemView.findViewById(R.id.self_buy_text_view);
            mSelfSellTextView = itemView.findViewById(R.id.self_sell_text_view);
            mSelfOverTextView = itemView.findViewById(R.id.self_over_text_view);
        }

        public void setItem(CorporationDetail item) {
            mDateTextView.setText(item.getDate());
            mForeignBuyTextView.setText(item.getForeignBuy());
            mForeignSellTextView.setText(item.getForeignSell());
            mForeignOverTextView.setText(item.getForeignOver());
            mInvestmentBuyTextView.setText(item.getInvestmentBuy());
            mInvestmentSellTextView.setText(item.getInvestmentSell());
            mInvestmentOverTextView.setText(item.getInvestmentOver());
            mSelfBuyTextView.setText(item.getSelfBuy());
            mSelfSellTextView.setText(item.getSelfSell());
            mSelfOverTextView.setText(item.getSelfOver());

        }
    }
}