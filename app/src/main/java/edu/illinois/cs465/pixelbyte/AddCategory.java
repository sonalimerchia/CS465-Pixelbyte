package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class AddCategory extends BottomSheetDialogFragment {
    Slider weightSlider;
    EditText nameInput;
    EditText weightInput;
    EditText dropsInput;
    EditText assignmentsInput;

    private int parseInt(EditText v) {
        String s = v.getText().toString();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void onClose() {
        if (nameInput == null) return;
        MainActivity m = (MainActivity) getActivity();
        m.inProgress_.categories_.add(new TemplateCategory(nameInput.getText().toString(), weightSlider.getValue(), parseInt(dropsInput), parseInt(assignmentsInput)));
        m.openDialog(BottomSheetCodes.NewTemplatePreview, "New Template");
    }

    private void linkWeightInputs() {
        weightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    float f = Float.parseFloat(editable.toString());
                    weightSlider.setValue(f);
                } catch (NumberFormatException e) {
                    // do nothing
                }
            }
        });

        weightSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {}

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                weightInput.setText(slider.getValue() + "%");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_add_category, container, false);

        // Initialize components
        weightSlider = (Slider) view.findViewById(R.id.weight_slider);
        weightInput = (EditText) view.findViewById(R.id.weight_input);
        nameInput = (EditText) view.findViewById(R.id.name_input);
        assignmentsInput = (EditText) view.findViewById(R.id.assignments_input);
        dropsInput = (EditText) view.findViewById(R.id.drops_input);

        linkWeightInputs();

        // Set formatter to slider
        LabelFormatter percentFormatter = new LabelFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value*100)/100.0+"%";
            }
        };
        weightSlider.setLabelFormatter(percentFormatter);

        // Set up back button
        ImageView backButton = (ImageView) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity m = (MainActivity) getActivity();
                m.openDialog(BottomSheetCodes.NewTemplatePreview, "New Template");
            }
        });

        // Set up done button TODO: send data when you click this
        TextView done = (TextView) view.findViewById(R.id.done_text);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClose();
            }
        });

        return view;
    }
}