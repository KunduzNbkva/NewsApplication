package com.example.newsapplication;

import android.os.Bundle;

import com.example.newsapplication.adapters.NewsAdapter;
import com.example.newsapplication.data.remote.NewsApiBuilder;
import com.example.newsapplication.models.NewsModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsapplication.Const.API_KEY;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        initViews();
    }

    public void initViews() {
        ImageView hamburger_toolbar = findViewById(R.id.hamburger_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_burger);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        NewsApiBuilder.getService().getTopNews(
                API_KEY,
                "ru")
                .enqueue(new Callback<NewsModel>() {
                    @Override
                    public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            adapter.setAdapterData(response.body().getArticles());
                            Log.e("response", "working" + response.body().getArticles().get(1));
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error of service", Toast.LENGTH_SHORT).show();
                        Log.e("onFailure", "fail is: " + t);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}