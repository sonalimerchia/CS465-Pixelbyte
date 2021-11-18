package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class CreateNewTemplate extends BottomSheetDialogFragment {
    ListView template;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set bottom sheet contents
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_template, container, false);

        // Create items from sample data
        List<TemplateCategory> items = TemplateCategory.createItems();

        // Create adapter to interpret data
        template = view.findViewById(R.id.template_in_progress);
        CategoryArrayAdapter arr = new CategoryArrayAdapter(view.getContext(), items, R.layout.creating_template_item);

        // Apply adapter to list
        template.setAdapter(arr);

        // Set up add category button
        TextView addCategory = (TextView) view.findViewById(R.id.add_category);
        addCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity m = (MainActivity) (getActivity());
                m.openDialog(BottomSheetCodes.AddCategory, "AddCategory");
            }
        });

        // Set up back button
        ImageView backButton = (ImageView) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity m = (MainActivity) (getActivity());
                m.openDialog(BottomSheetCodes.FindTemplate, "FindTemplate");
            }
        });

        // Set up finish button TODO: send data when you click this
        TextView finish = (TextView) view.findViewById(R.id.finish_text);
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity m = (MainActivity) (getActivity());
                m.openDialog.onDestroyView();
            }
        });

        return view;
    }
}