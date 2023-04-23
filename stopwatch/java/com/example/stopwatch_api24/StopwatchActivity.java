package com.example.stopwatch_api24;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private Stopwatch stopwatch;
    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        toggle = findViewById(R.id.toggle);

        Button settingsButton = findViewById(R.id.button2);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to start SettingsActivity
                Intent intent = new Intent(StopwatchActivity.this, SettingsActivity.class);
                startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
            }
        });

        isRunning = false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            String savedValue = savedInstanceState.getString("value");
            stopwatch = new Stopwatch(savedValue);
            boolean running = savedInstanceState.getBoolean("running");
            if (running) {
                enableStopwatch();
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", stopwatch.toString());
        outState.putBoolean("running", isRunning);
    }
    private void enableStopwatch() {
        toggle.setText("STOP");
        isRunning = true;
        handler = new Handler ();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopwatch.tick();
                    String text = stopwatch.toString();
                    display.setText(text);
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
    private void disableStopwatch() {
        isRunning = false;
        toggle.setText("START");
    }

    public void toggleClicked(View view) {
        if (isRunning) {
            disableStopwatch();
        } else {
            enableStopwatch();
        }
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

    public void resetClicked(View view) {
        stopwatch = new Stopwatch();
        display.setText(stopwatch.toString());
    }

} // class end