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
import android.widget.TextView;

import com.etp.stockapp.R;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.view_model.MainPageViewModel;
import com.google.gson.Gson;

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

        {
            Disposable disposable = mMainViewModel.mOutput.showRecyclerView.subscribe(new Consumer<List<CorporationDetail>>() {
                @Override
                public void accept(List<CorporationDetail> demoModels) throws Exception {
                    Log.d("///", "ShowRecyclerView success: " + new Gson().toJson(demoModels.get(0)));
                    mAdapter = new RecyclerViewAdapter(demoModels);
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

        mMainViewModel.mInput.callGetStockCorporation.onNext("20210114");
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {

        private List<CorporationDetail> mDemoModelList;

        public RecyclerViewAdapter(List<CorporationDetail> demoModelList) {
            mDemoModelList = demoModelList;
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
        }

        @Override
        public int getItemCount() {
            return mDemoModelList.size();
        }

        public CorporationDetail getItem(int position) {
            return mDemoModelList.get(position);
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mStockIDTextView;
        private TextView mStockNameTextView;
        private TextView mTransVolumeTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mStockIDTextView = itemView.findViewById(R.id.stock_id_text_view);
            mStockNameTextView = itemView.findViewById(R.id.stock_name_text_view);
            mTransVolumeTextView = itemView.findViewById(R.id.over_number_text_view);
        }

        public void setItem(CorporationDetail item) {

            mStockIDTextView.setText(item.getStockID());
            mStockNameTextView.setText(item.getStockName());
            mTransVolumeTextView.setText(item.getTotalOver());
        }
    }
}