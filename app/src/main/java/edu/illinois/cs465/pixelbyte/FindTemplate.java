package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassList.ClassData;
import edu.illinois.cs465.pixelbyte.RecommendedTemplateList.RecommendedTemplateAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class FindTemplate extends BottomSheetDialogFragment {
    ListView recommendedTemplates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_find_template, container, false);

        // Create items from sample data
        List<ClassData> items = createItems();

        // Create adapter to interpret data
        recommendedTemplates = view.findViewById(R.id.recommended_templates);
        RecommendedTemplateAdapter arr = new RecommendedTemplateAdapter(view.getContext(), items);

        // Apply adapter to list
        recommendedTemplates.setAdapter(arr);

        return view;
    }

    private List<ClassData> createItems() {
        List<ClassData> classes = new ArrayList<>();

        classes.add(new ClassData("CS 125", "A", 95.5, 0xffffff00, 90.0));
        classes.add(new ClassData("CS 465", "C+", 78.9, 0xff00ff00, 90.0));
        classes.add(new ClassData("CS 233", "B-", 83.9, 0xff00ffff, 80.0));

        addCategories(classes.get(0));
        addCategories(classes.get(1));
        addCategories(classes.get(2));

        return classes;
    }

    private void addCategories(ClassData cd) {
        cd.categories_.add(new TemplateCategory("Quizzes", 20.5, 2, 13));
        cd.categories_.add(new TemplateCategory("Homework", 15.0, 5, 25));
        cd.categories_.add(new TemplateCategory("Exams", 33.3, 0, 3));
    }
}