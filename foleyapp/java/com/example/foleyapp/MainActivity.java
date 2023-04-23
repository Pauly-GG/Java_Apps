package com.example.foleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button natureButton = findViewById(R.id.nature_button);
        natureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoleyActivity.class);
                intent.putExtra("category", "Nature");
                startActivity(intent);
            }
        });

        Button animalButton = findViewById(R.id.animal_button);
        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoleyActivity.class);
                intent.putExtra("category", "Animal");
                startActivity(intent);
            }
        });

        Button humanButton = findViewById(R.id.human_button);
        humanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoleyActivity.class);
                intent.putExtra("category", "Human");
                startActivity(intent);
            }
        });

        Button technologyButton = findViewById(R.id.technology_button);
        technologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoleyActivity.class);
                intent.putExtra("category", "Technology");
                startActivity(intent);
            }
        });
    }

    public void buttonPressed(View view) {
        Intent intent = new Intent(this, FoleyActivity.class);
        Button button = (Button) view;
        String category = button.getText().toString();
        intent.putExtra("category", category);
        startActivity(intent);
    }

    public enum SoundCategory {
        animal, human, nature, technology
    }
}
