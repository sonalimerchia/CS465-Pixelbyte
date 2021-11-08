package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateNewTemplate extends AppCompatActivity {
    ListView template;

    String[] names = {"Quizzes", "Homework", "Assignments"};
    double[] weights = {20.0, 15.0, 5.5};
    int[] drops = {2, 3, 0};
    int[] assignments = {15, 20, 30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_template);

        TemplateCategory[] items = createItems();

        template = findViewById(R.id.template_in_progress);
        CategoryArrayAdapter arr = new CategoryArrayAdapter(this, items);

        template.setAdapter(arr);
    }

    private TemplateCategory[] createItems() {
        TemplateCategory[] c = new TemplateCategory[names.length];
        for (int index = 0; index < names.length; ++index) {
            c[index] = new TemplateCategory(names[index], weights[index], drops[index], assignments[index]);
        }

        return c;
    }
}