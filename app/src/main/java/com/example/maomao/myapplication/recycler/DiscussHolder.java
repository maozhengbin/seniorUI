package com.travelto.maomao.travel_together.ui.recyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelto.maomao.travel_together.R;
import com.travelto.maomao.travel_together.data.dto.Discuss;
import com.travelto.maomao.travel_together.ui.recyclerview.click.OnItemClickListener;
import com.travelto.maomao.travel_together.util.other.InitHeard;

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
        discuss_tv_name.setText(discuss.getDiscuss());
        InitHeard initHeard = new InitHeard(itemView.getContext());
        initHeard.start(discuss_heard_icon, discuss.getUsernub());
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
