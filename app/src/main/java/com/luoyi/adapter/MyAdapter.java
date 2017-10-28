package com.luoyi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by CSIR on 2017/8/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    MyAdapter.ViewHolder viewHolder;
    public String[] datas = null;
    private int resource;
    public MyAdapter(String[] datas,int resource) {
        this.datas = datas;
        this.resource=resource;
    }

    //定义接口
    public interface OnItemClickListener{
        void OnItemClickListener(View view , int position);
        void OnItemLongClickListener(View view , int position);
    }
    //声明接口
    public OnItemClickListener onItemClickListener;

    //暴露方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        viewHolder=new MyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        //viewHolder.mTextView.setText(datas[position]);

        if (onItemClickListener!=null){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.OnItemClickListener(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.OnItemLongClickListener(holder.itemView,position);
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            //mTextView = (TextView) itemView.findViewById(R.id.tv);

        }
    }
}