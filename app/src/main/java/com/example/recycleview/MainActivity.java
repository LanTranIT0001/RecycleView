package com.example.recycleview;

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
    List countrylist = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        countrylist.add("Vietnam");
        countrylist.add("US");
        countrylist.add("China");
        countrylist.add("Japan");
        countrylist.add("Korea");
        countrylist.add("Thailand");

        recyclerView = findViewById(R.id.recyclerView);
        btLoad = findViewById(R.id.btLoad);
        btLoad.setOnClickListener(v -> {
                    MyAdapter myadapter = new MyAdapter(v.getContext(), countrylist);
                    recyclerView.setLayoutManager(
                            new LinearLayoutManager(this)
                    );
                    recyclerView.setAdapter(myadapter);
                }
        );
    }
}