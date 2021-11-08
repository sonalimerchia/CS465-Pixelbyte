package edu.illinois.cs465.pixelbyte.categoryCreation;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import edu.illinois.cs465.pixelbyte.R;

public class CategoryListManager {
    ListView template;

    String[] names = {"Quizzes", "Homework", "Assignments"};
    double[] weights = {20.0, 15.0, 5.5};
    int[] drops = {2, 3, 0};
    int[] assignments = {15, 20, 30};

    protected void onCreate(Bundle savedInstanceState, AppCompatActivity parentActivity) {
        parentActivity.setContentView(R.layout.activity_create_new_template);

        TemplateCategory[] items = createItems();

        template = parentActivity.findViewById(R.id.template_in_progress);
        CategoryArrayAdapter arr = new CategoryArrayAdapter(parentActivity, items);

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
