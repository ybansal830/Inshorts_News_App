package com.myfirst.inshortsnewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private EditText mEtSearch;
    private ImageButton mIbSearchBtn;
    private RecyclerView mRecyclerView;
    private List<DataNews> list;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mIbSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall();
            }
        });
    }

    private void apiCall() {
        ApiServices apiServices = Network.getInstance().create(ApiServices.class);
        if(mEtSearch.getText().toString().trim().length() < 3)
            Toast.makeText(this,"Please Enter proper category",
                    Toast.LENGTH_SHORT).show();
        else {
            String category = mEtSearch.getText().toString();
            apiServices.getData(category).enqueue(new Callback<ResponseNews>() {
                @Override
                public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                    list = response.body().getData();
                    setRecyclerView();
                }

                @Override
                public void onFailure(Call<ResponseNews> call, Throwable t) {

                }
            });
        }
    }

    private void initViews() {
        mEtSearch = findViewById(R.id.etSearch);
        mIbSearchBtn = findViewById(R.id.ibSearchBtn);
        mRecyclerView = findViewById(R.id.recyclerView);
        mWebView = findViewById(R.id.webView);
    }

    public void setRecyclerView(){
        NewsAdapter newsAdapter = new NewsAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(newsAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(int position) {
        mWebView.setVisibility(View.VISIBLE);
        mWebView.loadUrl(list.get(position).getReadMoreUrl());
    }
}