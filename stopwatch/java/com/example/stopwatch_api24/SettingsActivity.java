package com.example.stopwatch_api24;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SettingsActivity extends AppCompatActivity {

    private EditText mEditTexting;
    public static final int SETTINGS_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mEditTexting = findViewById(R.id.EditTexting);
        mEditTexting.setText("Settings");
    }

    public void SettingsClicked(View view) {
        String text = mEditTexting.getText().toString();
        int speed = Integer.parseInt(text);

        Intent intent = new Intent();
        intent.putExtra("speed", speed);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void doneClicked(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}
