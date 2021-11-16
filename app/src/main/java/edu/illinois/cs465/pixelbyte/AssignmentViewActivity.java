package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AssignmentViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);
        String classname = getIntent().getExtras().getString("ClassName");
        String category = getIntent().getExtras().getString("Category");
        this.setTitle(classname + " - " + category);
    }
}