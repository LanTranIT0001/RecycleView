package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ArticleDetailActivity extends AppCompatActivity {

    private Article currentArticle;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Hiện nút Back trên thanh tiêu đề (ActionBar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chi tiết bài viết");
        }

        // Lấy dữ liệu được đẩy qua từ Intent
        Intent intent = getIntent();
        currentArticle = (Article) intent.getSerializableExtra("ARTICLE");
        position = intent.getIntExtra("POSITION", -1);

        if (currentArticle != null) {
            // Tăng số lượt view
            currentArticle.incrementViews();
            // Thiết lập sẵn kết quả trả về MainActivity, để dù thoát bằng cách nào
            // (nút Quay lại, vuốt Back, nút Back hệ thống) thì lượt xem vẫn được cập nhật
            Intent resultIntent = new Intent();
            resultIntent.putExtra("UPDATED_ARTICLE", currentArticle);
            resultIntent.putExtra("POSITION", position);
            setResult(RESULT_OK, resultIntent);

            // Bind dữ liệu lên giao diện
            ImageView detailCover = findViewById(R.id.detailCover);
            TextView detailTitle = findViewById(R.id.detailTitle);
            TextView detailViews = findViewById(R.id.detailViews);
            TextView detailContent = findViewById(R.id.detailContent);

            detailCover.setImageResource(currentArticle.getImgCover());
            detailTitle.setText(currentArticle.getTitle());
            detailViews.setText("Views: " + currentArticle.getViews());
            detailContent.setText(currentArticle.getContent());
        }

        // Bắt sự kiện khi bấm nút Quay lại trên màn hình
        android.widget.Button btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    onBackPressed();
                }
            });
        }
    }

    // Bắt sự kiện khi người dùng ấn nút Back của hệ thống
    @Override
    public void onBackPressed() {
        // Đóng gói dữ liệu trả về cho MainActivity
        Intent returnIntent = new Intent();
        returnIntent.putExtra("UPDATED_ARTICLE", currentArticle);
        returnIntent.putExtra("POSITION", position);
        setResult(RESULT_OK, returnIntent);
        super.onBackPressed();
    }

    // Xử lý sự kiện khi bấm vào nút Back mũi tên trên thanh tiêu đề
    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
