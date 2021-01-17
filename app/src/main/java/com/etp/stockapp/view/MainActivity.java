package com.etp.stockapp.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etp.stockapp.R;
import com.etp.stockapp.custom.StockProperties;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.view_model.MainPageViewModel;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;

public class MainActivity extends BaseActivity {

    private MainPageViewModel mMainViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainViewModel = new ViewModelProvider(this).get(MainPageViewModel.class);
        if (mMainViewModel.isSetUp()) {

            subscribeSubject();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("///", "MainActivity Destroy");
    }

    private void subscribeSubject() {

        //region 訂閱 接收資料 顯示在RecyclerView
        {
            Disposable disposable = mMainViewModel.mOutput.showRecyclerView.subscribe(new Consumer<List<StockDetail>>() {
                @Override
                public void accept(List<StockDetail> stockEntityList) throws Exception {
                    Log.d("///", "ShowRecyclerView success");
                    mAdapter = new RecyclerViewAdapter(stockEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                    Log.d("///", "ShowRecyclerView error");
                }
            });
            addDisposable(disposable);
        }
        //endregion

        //region 訂閱 跳轉至 StockDetailActivity
        {
            Disposable disposable = mMainViewModel.mOutput.changeToStockDetailPage.subscribe(new Consumer<StockDetail>() {
                @Override
                public void accept(StockDetail stockDetail) throws Exception {

                    StockDetailActivity.startActivity(MainActivity.this, stockDetail);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("///", "changeToStockDetailPage disposable error: " + new Gson().toJson(throwable));
                }
            });
            addDisposable(disposable);
        }
        //endregion
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {

        private List<StockDetail> mStockEntityList;

        public RecyclerViewAdapter(List<StockDetail> stockEntityList) {
            mStockEntityList = stockEntityList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.setItem(getItem(position));

            RxView.clicks(recyclerViewHolder.itemView)
                    .map(unit -> getItem(position)).subscribe(mMainViewModel.mInput.recyclerViewItemClick);
        }

        @Override
        public int getItemCount() {
            return mStockEntityList.size();
        }

        public StockDetail getItem(int position) {
            return mStockEntityList.get(position);
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mStockIDTextView;
        private TextView mStockNameTextView;
        private TextView mTransVolumeTextView;
        private TextView mStockOpenPrizeTextView;
        private TextView mStockClosePrizeTextView;
        private TextView mStockRangeTextView;
        private ImageView mStockRangeStatusImageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mStockIDTextView = itemView.findViewById(R.id.stock_id_text_view);
            mStockNameTextView = itemView.findViewById(R.id.stock_name_text_view);
            mTransVolumeTextView = itemView.findViewById(R.id.over_number_text_view);
            mStockOpenPrizeTextView = itemView.findViewById(R.id.open_prize_text_view);
            mStockClosePrizeTextView = itemView.findViewById(R.id.close_prize_text_view);
            mStockRangeTextView = itemView.findViewById(R.id.range_text_view);
            mStockRangeStatusImageView = itemView.findViewById(R.id.range_image_view);
        }

        public void setItem(StockDetail item) {

            mStockIDTextView.setText(item.getStockID());
            mStockNameTextView.setText(item.getStockName());

            String totalOver = "0";
            if (item.getCorporationDetailList().size() > 0) {
                totalOver = item.getCorporationDetailList().get(item.getCorporationDetailList().size() - 1).getTotalOver();
            }
            mTransVolumeTextView.setText(totalOver);

            String openPrize = "";
            int openPrizeTextColor = R.color.white;
            String closePrize = "";
            int closePrizeTextColor = R.color.white;
            String range = "";
            int rangeImageViewID = R.drawable.ic_baseline_horizontal_rule_24;
            if (item.getStockRangeInfoDetailList().size() > 0) {
                StockRangeInfoDetail detail = item.getStockRangeInfoDetailList().get(item.getStockRangeInfoDetailList().size() - 1);
                openPrize = detail.getOpenPrize();
                closePrize = detail.getClosePrize();
                range = detail.getRange();

                switch (range.charAt(0)) {
                    case StockProperties.RangeStatus.UP: {
                        rangeImageViewID = R.drawable.ic_baseline_arrow_upward_24;
                        closePrizeTextColor = R.color.range_up;
                        break;
                    }
                    case StockProperties.RangeStatus.DOWN: {
                        rangeImageViewID = R.drawable.ic_baseline_arrow_downward_24;
                        closePrizeTextColor = R.color.range_down;
                        break;
                    }
                }

                double exPrizeDouble = Double.parseDouble(closePrize) - Double.parseDouble(range);
                double openPrizeDouble = Double.parseDouble(openPrize);

                if (openPrizeDouble > exPrizeDouble) {
                    openPrizeTextColor = R.color.range_up;
                }

                if (openPrizeDouble < exPrizeDouble) {
                    openPrizeTextColor = R.color.range_down;
                }
            }

            mStockOpenPrizeTextView.setText(openPrize);
            mStockOpenPrizeTextView.setTextColor(getResources().getColor(openPrizeTextColor, null));
            mStockClosePrizeTextView.setText(closePrize);
            mStockClosePrizeTextView.setTextColor(getResources().getColor(closePrizeTextColor, null));
            mStockRangeTextView.setText(range);
            mStockRangeStatusImageView.setImageResource(rangeImageViewID);
        }
    }
}