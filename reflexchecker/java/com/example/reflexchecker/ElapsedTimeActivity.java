package com.example.reflexchecker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ElapsedTimeActivity extends AppCompatActivity {
    private TextView elapsedTimeTextView;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elapsed_time);

        elapsedTimeTextView = findViewById(R.id.elapsed_time_text_view);
        startTime = getIntent().getLongExtra("start_time", 0);

        long elapsedTime = System.nanoTime() - startTime;
        int elapsedSeconds = (int) (elapsedTime / 1000000000);
        int elapsedSecondsTwoDigits = elapsedSeconds % 10;
        String elapsedTimeText = getString(R.string.elapsed_time, elapsedSecondsTwoDigits);
        elapsedTimeTextView.setText(elapsedTimeText);
    }
}
