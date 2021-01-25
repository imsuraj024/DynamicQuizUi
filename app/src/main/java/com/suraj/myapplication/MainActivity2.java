package com.suraj.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        type = findViewById(R.id.question_type);

    }

    public void submit(View view) {

        String question = type.getText().toString();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra("question_pattern", question);
        startActivity(intent);

    }
}