package com.example.maomao.myapplication.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maomao.myapplication.R;

/**
 * Created by maomao on 2017/3/16.
 */

public class DiscussHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener;


    private TextView discuss_tv_name;
    private ImageView discuss_heard_icon;

    public DiscussHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        discuss_tv_name = (TextView) itemView.findViewById(R.id.discuss_tv_name);
        discuss_heard_icon = (ImageView) itemView.findViewById(R.id.discuss_heard_icon);
    }

    public void setData(Discuss discuss) {
        discuss_tv_name.setText("userid："+discuss.getUser_id()+"，id："+discuss.getId()+",chatcomment:"+discuss.getChat_comment());
        Glide.with(itemView.getContext()).load(discuss.getHeader_url()).asBitmap().into(discuss_heard_icon);
//        InitHeard initHeard = new InitHeard();
//        initHeard.start(discuss_heard_icon, discuss.getUsernub());
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
