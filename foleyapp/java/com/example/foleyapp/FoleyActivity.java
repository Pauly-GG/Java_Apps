package com.example.foleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class FoleyActivity extends AppCompatActivity {

    @Override public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "started";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "moved";
                break;
            case MotionEvent.ACTION_UP:
                action = "ended";
                break;
        }
        Log.i("TouchableActivity", String.format(Locale.getDefault(),
                "%.2f %.2f %s", x, y, action));
        return true;
    }

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foley);

        // Create an instance of AudioManager
        audioManager = new AudioManager(this);

        // Get the selected sound category from the Intent extras
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Play the sound for the selected category
        if (category != null) {
            switch (category) {
                case "Nature":
                    audioManager.playSound(AudioManager.SoundCategory.nature, 1.0f, 1.0f);
                    break;
                case "Animal":
                    audioManager.playSound(AudioManager.SoundCategory.animal, 1.0f, 1.0f);
                    break;
                case "Human":
                    audioManager.playSound(AudioManager.SoundCategory.human, 1.0f, 1.0f);
                    break;
                case "Technology":
                    audioManager.playSound(AudioManager.SoundCategory.technology, 1.0f, 1.0f);
                    break;
            }
        }
    }
}
