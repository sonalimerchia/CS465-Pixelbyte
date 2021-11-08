package edu.illinois.cs465.pixelbyte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {//implements View.OnClickListener {
    private static final int CREATE_CLASS_CODE = 1;

    private Button addClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons on Home Screen
        addClassButton = (Button) findViewById(R.id.add_class_button);

        //OnClick Listeners
        //addClassButton.setOnClickListener(this);
    }

    /*
    public void onClick (View v) {
        if (v.getId() == R.id.add_class_button) {

            Intent intent = new Intent (MainActivity.this, CreateClassActivity.class);
            startActivity(intent);
        }

    }
     */
}

