package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/* This is the first activity page to create a Class Object.
TODO: Navigation + Passing information back to Home Screen
 */
public class CreateClass extends BottomSheetDialogFragment {
    BottomSheetDialogFragment openDialog;
    // Text Views
    private TextView nextLable;

    // EditText Views
    private EditText courseNameInput;

    // Button Views
    private Button redButton;
    private Button greenButton;
    private Button blueButton;

    // Image Views
    private ImageView nextArrow;

    // Spinner (Drop Down Menu)
    private Spinner courseDeptSpinner;


    public static CreateClass newInstance() {
        return new CreateClass();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set bottom sheet contents
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_template, container, false);

        /* instantiate views and attach listeners */
        //EditText
        courseNameInput = (EditText) view.findViewById(R.id.course_edit_name);
        courseNameInput.setAllCaps(false);
        courseNameInput.setHint(R.string.course_name_hint);

        //Color Buttons
        redButton = (Button) view.findViewById(R.id.red_button);
        greenButton = (Button) view.findViewById(R.id.green_button);
        blueButton = (Button) view.findViewById(R.id.blue_button);

        //TODO: Custom Color selector button.

        redButton.setOnClickListener((View.OnClickListener) this);
        greenButton.setOnClickListener((View.OnClickListener) this);
        blueButton.setOnClickListener((View.OnClickListener) this);

        //Navigation Buttons
        nextLable = (TextView) view.findViewById(R.id.next_label);
        nextArrow = (ImageView) view.findViewById(R.id.next_arrow);

        nextLable.setOnClickListener((View.OnClickListener) this);
        nextArrow.setOnClickListener((View.OnClickListener) this);

        //Spinner (Drop-down menu)
        courseDeptSpinner = (Spinner) view.findViewById(R.id.course_dept_spinner);
        courseDeptSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(((CreateClass) this).getContext(),
                R.array.class_departments_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        courseDeptSpinner.setAdapter(adapter);

        return view;
    }

    private void openDialog(BottomSheetCodes code, String bottomSheetName) {
        switch (code) {
            case NewTemplatePreview:
                openDialog = new CreateNewTemplate();
                break;
            case AddCategory:
                openDialog = new AddCategory();
                break;
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }
}