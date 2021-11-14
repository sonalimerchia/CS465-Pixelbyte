package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class AddCategory extends BottomSheetDialogFragment {
    Slider weightSlider;
    EditText nameInput;
    EditText weightInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_add_category, container, false);

        // Initialize components
        weightSlider = (Slider) view.findViewById(R.id.weight_slider);
        weightInput = (EditText) view.findViewById(R.id.weight_input);
        nameInput = (EditText) view.findViewById(R.id.name_input);


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
                getFragmentManager().beginTransaction().remove(AddCategory.this).commit();
            }
        });

        // Set up done button TODO: send data when you click this
        TextView done = (TextView) view.findViewById(R.id.done_text);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(AddCategory.this).commit();
            }
        });

        return view;
    }
}