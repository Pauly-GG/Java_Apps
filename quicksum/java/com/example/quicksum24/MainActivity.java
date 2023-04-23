package com.example.quicksum24;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int totalSum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();
        System.out.println("button text" + text);
        int n = Integer.parseInt(text);
        totalSum += n;

        TextView textView = findViewById(R.id.sum);
        String result = "" + totalSum;
        textView.setText(result);
    }

    public void clearClicked(View view) {
        totalSum = 0;
        TextView textView = findViewById(R.id.sum);
        textView.setText("0");
    }

}