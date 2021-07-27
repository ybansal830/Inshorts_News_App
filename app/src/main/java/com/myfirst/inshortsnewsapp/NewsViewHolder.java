package com.myfirst.inshortsnewsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvNews;
    private TextView mTvTitle,mTvATD,mTvContent;
    private CardView mCvNews;
    private OnItemClickListener onItemClickListener;

    public NewsViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        initViews();
    }

    private void initViews() {
        mIvNews = itemView.findViewById(R.id.ivNews);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mTvATD = itemView.findViewById(R.id.tvATD);
        mTvContent = itemView.findViewById(R.id.tvContent);
        mCvNews = itemView.findViewById(R.id.cvNews);
        mCvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(getAdapterPosition());
            }
        });
    }
    public void setData(DataNews dataNews){
        Glide.with(mIvNews).load(dataNews.getImageUrl()).into(mIvNews);
        mTvTitle.setText(dataNews.getTitle());
        mTvATD.setText("short by " + dataNews.getAuthor() + " / " + dataNews.getTime() + " on " +
                dataNews.getDate());
        mTvContent.setText(dataNews.getContent());
    }
}
