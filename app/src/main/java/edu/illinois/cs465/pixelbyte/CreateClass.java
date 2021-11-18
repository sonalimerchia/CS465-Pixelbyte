package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateClass extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_class, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.department_selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        View nextButton = view.findViewById(R.id.to_template_finder);
        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity m = (MainActivity) getActivity();
                    m.openDialog(BottomSheetCodes.FindTemplate, "Find Template");
                }
            });
        }

        return view;
    }


}