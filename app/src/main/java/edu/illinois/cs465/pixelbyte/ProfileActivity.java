package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set up spinners
        Spinner terms = findViewById(R.id.spinner_term);
        ArrayList<String> termItems = new ArrayList(Arrays.asList("SP 2022", "FA 2021", "SP 2021", "FA 2020"));
        terms.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, termItems));

        Spinner intervals = findViewById(R.id.spinner_how_often);
        ArrayList<String> intervalItems = new ArrayList(Arrays.asList("once per month", "once per week", "once per day"));
        intervals.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, intervalItems));

        // Set up back button
        ImageView backButton = (ImageView) findViewById(R.id.back_button);
        Intent intent = new Intent(this, MainActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}