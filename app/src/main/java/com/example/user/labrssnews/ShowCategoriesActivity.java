package com.example.user.labrssnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShowCategoriesActivity extends AppCompatActivity {
    private Button chooseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        chooseBtn = (Button)findViewById(R.id.chooseButton);

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = getStatus();
                Intent intent = new Intent(ShowCategoriesActivity.this, MainActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
    }

    public String getStatus() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.catRadioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        return ((RadioButton)radioGroup.findViewById(radioId)).getText().toString();
    }
}