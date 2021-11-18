package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateClass extends BottomSheetDialogFragment implements View.OnClickListener {
    int color_;

    public void onClick(View v) {
        if (v.getId() == R.id.red_button) color_ = 0xffff0000;
        else if (v.getId() == R.id.yellow_button) color_ = 0xffffff00;
        else if (v.getId() == R.id.green_button) color_ = 0xff00ff00;
        else if (v.getId() == R.id.blue_button) color_ = 0xff0000ff;
    }

    public void onClose() {
        MainActivity m = (MainActivity) getActivity();

        String name = ((TextView) this.getView().findViewById(R.id.class_name_input)).getText().toString();
        Spinner selector = (Spinner)(this.getView().findViewById(R.id.department_selector));
        String department = selector.toString();
        m.startNewClass(name, color_, department);
        m.openDialog(BottomSheetCodes.FindTemplate, "Find Template");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_class, container, false);
        color_ = 0xffff0000;

        view.findViewById(R.id.red_button).setOnClickListener(this);
        view.findViewById(R.id.yellow_button).setOnClickListener(this);
        view.findViewById(R.id.green_button).setOnClickListener(this);
        view.findViewById(R.id.blue_button).setOnClickListener(this);

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
                    onClose();
                }
            });
        }

        return view;
    }


}