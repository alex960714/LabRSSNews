package com.example.user.labrssnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button catBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catBtn = (Button)findViewById(R.id.categoryButton);

        catBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowCategoriesActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        String category = getIntent().getStringExtra("category");
        if (category == null){
            category = "ALL";
        }
        ((TextView)findViewById(R.id.titleTextView)).setText(category);
        NewsSource.getContent(category);
    }
}
