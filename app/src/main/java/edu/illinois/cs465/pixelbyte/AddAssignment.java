package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class AddAssignment extends BottomSheetDialogFragment {
    List<String> categories;
    Spinner categorySelect_;
    EditText nameInput_;
    EditText earnedInput_;
    EditText possibleInput_;

    public AddAssignment(List<TemplateCategory> c) {
        categories = new ArrayList<>();
        for (TemplateCategory tc : c) {
            categories.add(tc.name_);
        }
    }

    private double parseDouble(EditText textField) {
        try {
            String contents = textField.getText().toString();
            return Double.parseDouble(contents);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public void onClose() {

        String name = nameInput_.getText().toString();
        String category = categorySelect_.getSelectedItem().toString();
        double earned = parseDouble(earnedInput_);
        double possible = parseDouble(possibleInput_);

        ClassActivity activity = (ClassActivity) getActivity();
        activity.addAssignment(new Assignment(name, earned, possible), category);
        activity.updateSummary();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_add_assignment, container, false);

        categorySelect_ = (Spinner) view.findViewById(R.id.category_select);
        nameInput_ = (EditText) view.findViewById(R.id.name_input);
        earnedInput_ = (EditText) view.findViewById(R.id.earned_input);
        possibleInput_ = (EditText) view.findViewById(R.id.possible_input);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySelect_.setAdapter(adapter);

        // Enable finish button
        Button finish = view.findViewById(R.id.finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClose();
                onDestroyView();
            }
        });

        return view;
    }
}