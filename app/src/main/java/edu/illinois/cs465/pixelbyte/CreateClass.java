package edu.illinois.cs465.pixelbyte;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateClass extends BottomSheetDialogFragment implements View.OnClickListener {
    TextView currentColor_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set bottom sheet contents
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_class, container, false);

        //Color Buttons
        view.findViewById(R.id.red_button).setOnClickListener(this);
        view.findViewById(R.id.yellow_button).setOnClickListener(this);
        view.findViewById(R.id.green_button).setOnClickListener(this);
        view.findViewById(R.id.blue_button).setOnClickListener(this);

        //Select Course Department (Drop-down menu)
        Spinner spinner = (Spinner) view.findViewById(R.id.department_selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Next Button
        View nextButton = view.findViewById(R.id.to_template_finder);
        //ImageView nextArrow = view.findViewById(R.id.next_arrow_to_template);
        //nextArrow.setOnClickListener(this);

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

    public void onClick(View v) {
        currentColor_ = (TextView) v;
    }

    private int getColorId(TextView b) {
        if (b.getId() == R.id.red_button) return R.color.uiuc_salmon;
        else if (b.getId() == R.id.yellow_button) return R.color.uiuc_dark_yellow;
        else if (b.getId() == R.id.green_button) return R.color.uiuc_citron;
        else if (b.getId() == R.id.blue_button) return R.color.uiuc_gray_blue;
        return R.color.uiuc_salmon;
    }

    public void onClose() {
        MainActivity m = (MainActivity) getActivity();

        String name = ((TextView) this.getView().findViewById(R.id.class_name_input)).getText().toString();
        Spinner selector = (Spinner)(this.getView().findViewById(R.id.department_selector));
        String department = selector.getSelectedItem().toString();

        int colorId = getColorId(currentColor_);
        m.startNewClass(name, getResources().getColor(colorId), department);
        m.openDialog(BottomSheetCodes.FindTemplate, "Find Template");
    }

}