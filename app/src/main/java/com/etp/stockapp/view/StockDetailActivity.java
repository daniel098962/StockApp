package com.etp.stockapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etp.stockapp.R;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.view_model.StockDetailViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class StockDetailActivity extends BaseActivity {

    public static void startActivity(Context context, StockDetail stockDetail) {

        Intent intent = new Intent();
        intent.setClass(context, StockDetailActivity.class);
        intent.putExtra("gson", new Gson().toJson(stockDetail));
        context.startActivity(intent);
    }

    private StockDetailViewModel mStockDetailViewModel;
    private StockDetail mStockDetail;

    private TextView mStockIDTextView;
    private TextView mStockNameTextView;
    private TextView mOpenPrizeTextView;
    private TextView mClosePrizeTextView;
    private TextView mHighestPrizeTextView;
    private TextView mLowestPrizeTextView;
    private TextView mRangeTextView;
    private TextView mDealStockTextView;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        mStockIDTextView = findViewById(R.id.stock_id_text_view);
        mStockNameTextView = findViewById(R.id.stock_name_text_view);
        mOpenPrizeTextView = findViewById(R.id.open_prize_text_view);
        mClosePrizeTextView = findViewById(R.id.close_prize_text_view);
        mHighestPrizeTextView = findViewById(R.id.highest_prize_text_view);
        mLowestPrizeTextView = findViewById(R.id.lowest_prize_text_view);
        mRangeTextView = findViewById(R.id.range_text_view);
        mDealStockTextView = findViewById(R.id.deal_stock_text_view);
        mRecyclerView = findViewById(R.id.corporation_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStockDetail = new Gson().fromJson(this.getIntent().getStringExtra("gson"), new TypeToken<StockDetail>(){}.getType());

        StockRangeInfoDetail infoDetail = mStockDetail.getStockRangeInfoDetailList().get(mStockDetail.getStockRangeInfoDetailList().size() - 1);
        mStockIDTextView.setText(infoDetail.getStockID());
        mStockNameTextView.setText(mStockDetail.getStockName());
        mOpenPrizeTextView.setText(infoDetail.getOpenPrize());
        mClosePrizeTextView.setText(infoDetail.getClosePrize());
        mHighestPrizeTextView.setText(infoDetail.getHighestPrize());
        mLowestPrizeTextView.setText(infoDetail.getLowestPrize());
        mRangeTextView.setText(infoDetail.getRange());
        mDealStockTextView.setText(infoDetail.getDealStock());

        mAdapter = new RecyclerViewAdapter(mStockDetail.getCorporationDetailList());
        mRecyclerView.setAdapter(mAdapter);
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