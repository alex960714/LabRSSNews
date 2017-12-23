package com.example.user.labrssnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NewsAdapter adapter;
    private Button refreshBtn;
    private Button catBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshBtn = (Button)findViewById(R.id.refreshButton);
        catBtn = (Button)findViewById(R.id.categoryButton);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        catBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowCategoriesActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        initTable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void initTable() {
        data = db.getData();

        ListView table = (ListView) findViewById(R.id.table);
        adapter = new TableAdapter(data, this);
        table.setAdapter(adapter);
    }

    public void updateData() {
        if (data != null) {
            data.clear();
            data.addAll(db.getData());
            adapter.notifyDataSetChanged();
        }
    }
}
