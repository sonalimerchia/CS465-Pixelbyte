package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class CreateNewTemplate extends BottomSheetDialogFragment {
    ListView template;

    String[] names = {"Quizzes", "Homework", "Assignments"};
    double[] weights = {20.0, 15.0, 5.5};
    int[] drops = {2, 3, 0};
    int[] assignments = {15, 20, 30};

    BottomSheetDialogFragment openDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set bottom sheet contents
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_template, container, false);

        // Create items from sample data
        TemplateCategory[] items = createItems();

        // Create adapter to interpret data
        template = view.findViewById(R.id.template_in_progress);
        CategoryArrayAdapter arr = new CategoryArrayAdapter(view.getContext(), items);

        // Apply adapter to list
        template.setAdapter(arr);

        // Set up add category button
        TextView addCategory = (TextView) view.findViewById(R.id.add_category);
        addCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openDialog("New Category");
            }
        });

        // Set up back button
        ImageView backButton = (ImageView) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(CreateNewTemplate.this).commit();
            }
        });

        // Set up finish button TODO: send data when you click this
        TextView finish = (TextView) view.findViewById(R.id.finish_text);
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(CreateNewTemplate.this).commit();
            }
        });

        return view;
    }

    private TemplateCategory[] createItems() {
        TemplateCategory[] c = new TemplateCategory[names.length];
        for (int index = 0; index < names.length; ++index) {
            c[index] = new TemplateCategory(names[index], weights[index], drops[index], assignments[index]);
        }

        return c;
    }


    private void openDialog(String bottomSheetName) {
        openDialog = new AddCategory();
        openDialog.show(getActivity().getSupportFragmentManager(), bottomSheetName);
    }
}