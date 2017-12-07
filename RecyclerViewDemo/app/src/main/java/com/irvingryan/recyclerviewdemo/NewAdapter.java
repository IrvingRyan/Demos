package com.irvingryan.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by wentao on 2016/8/23.
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<String> mDatas;
    ArrayList<String> strings = new ArrayList<>();


    public NewAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        mDatas = datas;
        strings.add(mDatas.get(0));
        strings.add(mDatas.get(4));
        strings.add(mDatas.get(5));
        strings.add(mDatas.get(2));
        strings.add(mDatas.get(1));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.new_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text1.setText(mDatas.get(position));
        holder.recyclerView.setAdapter(new MyAdapter(mContext, strings));
        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: recyclerView");
            }
        });

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: rl");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text1;
        @BindView(R.id.rv)
        RecyclerView recyclerView;
        @BindView(R.id.list_item)
        RelativeLayout rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
