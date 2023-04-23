package com.example.watertrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    // Declare private variables for the EditText and RadioGroup views
    private EditText waterGoalEditText;
    private RadioGroup unitsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize the EditText and RadioGroup views
        waterGoalEditText = findViewById(R.id.water_goal_edit_text);
        unitsRadioGroup = findViewById(R.id.units_radio_group);

        // Initialize the RadioButtons and Save Button views
        RadioButton millilitersRadioButton = findViewById(R.id.milliliters_radio_button);
        RadioButton litersRadioButton = findViewById(R.id.liters_radio_button);
        Button saveButton = findViewById(R.id.save_button);

        // Set initial units radio button
        millilitersRadioButton.setChecked(true);

        // Set the initial checked RadioButton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save settings and go back to MainActivity
                double waterGoal = 0;
                boolean isMilliliters = true;

                // Get the user-entered water goal and convert it to a float number
                String waterGoalStr = waterGoalEditText.getText().toString().trim();
                if (!waterGoalStr.isEmpty()) {
                    waterGoal = Double.parseDouble(waterGoalStr);
                }

                // Get the user-selected units and set a boolean flag accordingly
                int unitsRadioButtonId = unitsRadioGroup.getCheckedRadioButtonId();
                if (unitsRadioButtonId == R.id.liters_radio_button) {
                    isMilliliters = false;
                }

                // Create an intent to return the settings to MainActivity
                Intent intent = new Intent();
                intent.putExtra("water_goal", waterGoal);
                intent.putExtra("is_milliliters", isMilliliters);
                setResult(RESULT_OK, intent);

                // Finish this activity and return to MainActivity
                finish();
            }
        });
    }
}