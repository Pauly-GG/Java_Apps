package com.example.spiritlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor gravitySensor;
    private SpiritLevelView spiritLevelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SpiritLevelView
        spiritLevelView = findViewById(R.id.spirit_level_view);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        // Get the list of all sensors
        List<Sensor> sensorList = manager.getSensorList(Sensor.TYPE_ALL);

        // Log the names of all sensors
        for (Sensor sensor : sensorList) {
            Log.i("SENSOR", sensor.getName());
        }

        // Check if the magnetometer sensor is available
        if (manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            // Success! There's a magnetometer.
            Log.i("SENSOR", "Magnetometer sensor available");
        } else {
            // Failure! No magnetometer.
            Log.i("SENSOR", "Magnetometer sensor not available");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String message = String.format(Locale.getDefault(),
                "%s: %d", sensor.getName(), accuracy);
        Log.i("MainActivity", message);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final String[] axes = new String[]{"x", "y", "z"};
        final String format = "%s %.2f ";

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < axes.length; ++i) {
            message.append(String.format(Locale.getDefault(),
                    format, axes[i], event.values[i]));
        }
        Log.i("MainActivity", message.toString());

        float x = event.values[0];
        float y = event.values[1];

        spiritLevelView.setBubble(x, y);

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
