package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class CreateNewTemplate extends BottomSheetDialogFragment {
    ListView template;

    String[] names = {"Quizzes", "Homework", "Assignments"};
    double[] weights = {20.0, 15.0, 5.5};
    int[] drops = {2, 3, 0};
    int[] assignments = {15, 20, 30};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_create_new_template, container, false);

        TemplateCategory[] items = createItems();

        template = view.findViewById(R.id.template_in_progress);
        CategoryArrayAdapter arr = new CategoryArrayAdapter(view.getContext(), items);

        template.setAdapter(arr);

        return view;
    }

    private TemplateCategory[] createItems() {
        TemplateCategory[] c = new TemplateCategory[names.length];
        for (int index = 0; index < names.length; ++index) {
            c[index] = new TemplateCategory(names[index], weights[index], drops[index], assignments[index]);
        }

        return c;
    }
}