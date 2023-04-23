package com.example.watertrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView waterAmountTextView;
    private EditText subtractAmountEditText;
    private double waterAmount = 0;
    private boolean isMilliliters = true;
    private ImageView waterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by ID
        waterAmountTextView = findViewById(R.id.water_amount_text_view);
        subtractAmountEditText = findViewById(R.id.subtract_amount_edit_text);
        Button subtractButton = findViewById(R.id.subtract_button);
        Button settingsButton = findViewById(R.id.settings_button);
        waterImage = findViewById(R.id.water_image);

        // Set initial water amount and display
        waterAmount = 2000; // default to 2000mL
        waterAmountTextView.setText(getWaterAmountString());

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Subtract entered amount from water amount
                String subtractAmountStr = subtractAmountEditText.getText().toString().trim();
                if (subtractAmountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an amount to subtract", Toast.LENGTH_SHORT).show();
                    return;
                }
                double subtractAmount = Double.parseDouble(subtractAmountStr);
                waterAmount -= subtractAmount;
                if (waterAmount < 0) {
                    waterAmount = 0;
                }
                waterAmountTextView.setText(getWaterAmountString());
                subtractAmountEditText.setText("");
                updateWaterImage();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to Settings activity
                Intent intent = new Intent(MainActivity.this, com.example.watertrackerapp.SettingsActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    // Sets image depending on amount of water left to drink
    private void updateWaterImage() {
        if (waterAmount >= 2000) {
            waterImage.setImageResource(R.drawable.water_full);
        } else if (waterAmount >= 1500) {
            waterImage.setImageResource(R.drawable.water_75);
        } else if (waterAmount >= 1000) {
            waterImage.setImageResource(R.drawable.water_50);
        } else if (waterAmount >= 500) {
            waterImage.setImageResource(R.drawable.water_25);
        } else {
            waterImage.setImageResource(R.drawable.water_low);
        }
    }

    // Update water amount string based on current settings
    @SuppressLint("DefaultLocale")
    private String getWaterAmountString() {
        String amountUnit;
        double displayAmount;

        if (isMilliliters) {
            amountUnit = "mL";
            displayAmount = waterAmount;
        } else {
            amountUnit = "L";
            displayAmount = waterAmount / 1000;
        }

        return String.format("%.1f", displayAmount) + " " + amountUnit;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Update water amount and units based on settings
                waterAmount = data.getDoubleExtra("water_goal", 0);
                isMilliliters = data.getBooleanExtra("is_milliliters", true);
                waterAmountTextView.setText(getWaterAmountString());
            }
        }
    }
}
