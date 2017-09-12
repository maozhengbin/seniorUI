package com.example.maomao.myapplication.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.maomao.myapplication.R;

import java.util.List;

/**
 * Created by maomao on 2017/3/16.
 */

public class DiscussAdapter extends RecyclerView.Adapter<DiscussHolder>{
    private List<Discuss>list;

    private Context context;
    private OnItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public DiscussAdapter(Context context, List<Discuss> list) {
        this.list = list;
        this.context = context;

    }


    @Override
    public DiscussHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DiscussHolder(LayoutInflater.from(context).inflate(R.layout.item_discuss, parent, false));
    }

    @Override
    public void onBindViewHolder(DiscussHolder holder, int position) {

        holder.setData(list.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
