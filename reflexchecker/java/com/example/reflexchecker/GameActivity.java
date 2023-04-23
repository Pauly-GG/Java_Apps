package com.example.reflexchecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final int[] drawables = {
            R.drawable.baseline_account_balance_black_48,
            R.drawable.baseline_add_shopping_cart_black_48,
            R.drawable.baseline_rocket_launch_black_48,
            R.drawable.baseline_savings_black_48,
            R.drawable.baseline_tips_and_updates_black_48,
            R.drawable.baseline_verified_user_black_48
    };

    private static final String[] drinks = {
            "Coca Cola", "Sprite", "Pepsi", "Fanta", "Mountain Dew"
    };

    private static final String[] fruits = {
            "Apple", "Banana", "Orange", "Kiwi", "Grapes"
    };

    private void addRandomImage() {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.image, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        ImageView image = (ImageView) lastChild;

        int index = random.nextInt(drawables.length);
        Drawable drawable = ContextCompat.getDrawable(this, drawables[index]);
        image.setImageDrawable(drawable);
    }

    private final Random random = new Random();
    private Button doneButton;
    private TextView elapsedTimeTextView;
    private long startTime;

    private void setupDescription(int taskID, int arrayID) {
        TextView task = findViewById(taskID);
        String[] descriptions = getResources().getStringArray(arrayID);

        int i = random.nextInt(descriptions.length);
        task.setText(descriptions[i]);
    }

    public void onDoneButtonClick(View view) {
        long elapsedTime = System.nanoTime() - startTime;
        int elapsedSeconds = (int) (elapsedTime / 1000000000); // Divide by 1 billion to convert from nanoseconds to seconds

        // Update the text view with the elapsed time in seconds
        elapsedTimeTextView.setText(String.format("%d seconds", elapsedSeconds));

        doneButton.setEnabled(false);
    }


    private void addRandomCheckboxes(int arrayID) {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.checkboxes, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        TableRow row = (TableRow) lastChild;

        // Add only three checkboxes
        for (int i = 0; i < 3; i++) {
            View child = row.getChildAt(i);
            if (child instanceof CheckBox) {
                CheckBox checkbox = (CheckBox) child;
                String[] options = getResources().getStringArray(arrayID);
                int index = random.nextInt(options.length);
                checkbox.setText(options[index]);
                checkbox.setChecked(random.nextBoolean());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupDescription(R.id.task1, R.array.task1_descriptions);
        setupDescription(R.id.task2, R.array.task2_descriptions);

        ViewGroup gameRows = findViewById(R.id.game_rows);

        int lastOptionSet = -1; // Start with neither drinks nor fruits
        for (int i = 0; i < 2; i++) {
            addRandomImage();
            if (lastOptionSet == 0) {
                addRandomCheckboxes(R.array.fruits);
                lastOptionSet = 1;
            } else {
                addRandomCheckboxes(R.array.drinks);
                lastOptionSet = 0;
            }
        }
        Button doneButton = findViewById(R.id.done_button);
        TextView elapsedTimeTextView = findViewById(R.id.elapsed_time_text_view);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long elapsedTime = System.nanoTime() - startTime;

                Intent intent = new Intent(GameActivity.this, ElapsedTimeActivity.class);
                intent.putExtra("elapsedTime", elapsedTime);
                startActivity(intent);

                doneButton.setVisibility(View.GONE);
            }
        });
        startTime = System.nanoTime();
    }
}
