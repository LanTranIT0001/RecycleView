package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btLoad;
    
    // Đổi từ static sang biến thông thường
    private List<Article> articleList = new ArrayList<>();
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(android.view.View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        if (articleList.isEmpty()) {
            articleList.add(new Article("Apple Watch Series 11 42mm", "Apple Watch Series 11 42mm với màn hình LTPO3 Retina Always-on có độ sáng đến 2000 nits, tấm nền OLED góc rộng cho hiển thị rõ nét trong nhiều điều kiện.", R.drawable.img_1_2));
            articleList.add(new Article("IPhone 17 Pro", "iPhone 17 sở hữu màn hình ProMotion 120Hz 6.3 inch lớn hơn, chip Apple A19 mạnh mẽ và hệ thống camera kép 48MP nâng cấp.", R.drawable.img_1_3));
            articleList.add(new Article("iPhone 18 Pro 2TB", "iPhone 18 Pro 2TB có bốn màu thanh lịch: Đen, Bạc, Băng Thanh và Đỏ burgundy hoàn toàn mới.", R.drawable.img_1_4));
        }

        recyclerView = findViewById(R.id.recyclerView);
        btLoad = findViewById(R.id.btLoad);
        
        // Cấu hình adapter và bắt sự kiện click
        articleAdapter = new ArticleAdapter(this, articleList, new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article article, int position) {
                // Đóng gói dữ liệu gửi qua DetailActivity
                Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
                intent.putExtra("ARTICLE", article);
                intent.putExtra("POSITION", position);
                // Mở DetailActivity và chờ kết quả trả về
                startActivityForResult(intent, 1);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Gán adapter ngay lập tức để hiện data luôn không cần chờ bấm nút
        recyclerView.setAdapter(articleAdapter);
        
        btLoad.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                recyclerView.setAdapter(articleAdapter);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Article updatedArticle = (Article) data.getSerializableExtra("UPDATED_ARTICLE");
            int position = data.getIntExtra("POSITION", -1);

            if (position != -1 && updatedArticle != null) {
                // Cập nhật vào list
                articleList.set(position, updatedArticle);
                // Báo cho Adapter biết vị trí này đã thay đổi
                articleAdapter.notifyItemChanged(position);
            }
        }
    }
}