package edu.illinois.cs465.pixelbyte;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/* This is the first activity page to create a Class Object.

TODO: Return a Class Object on completion? (Keep user input between CreateClass screens).
 Update names/buttons to match other screens. Navigation.
 */
public class CreateNewClass extends BottomSheetDialogFragment {
    // Text Views
    private TextView addClassTitle;
    private TextView courseNameLabel;
    private TextView courseColorLabel;
    private TextView courseDeptLabel;

    private EditText courseNameInput;

    // Button Views
    private Button redButton;
    private Button greenButton;
    private Button blueButton;

    private Button nextButton;

    // Spinner (Drop Down Menu)
    private Spinner courseDeptSpinner;

    public static CreateNewClass newInstance() {
        return new CreateNewClass();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_class, container, false);

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
        nextButton = (Button) view.findViewById(R.id.next_button);

        nextButton.setOnClickListener((View.OnClickListener) this);

        //Spinner (Drop-down menu)
        courseDeptSpinner = (Spinner) view.findViewById(R.id.course_dept_spinner);
        courseDeptSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(((CreateNewClass) this).getContext(),
                R.array.class_departments_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        courseDeptSpinner.setAdapter(adapter);

        return view;
    }
}

/*To show the dialog fragment in the activity:

CreateNewClass createNewClassFragment = CreateNewClass.newInstance();
createNewClassFragment.show(getSupportFragmentManager(), "create_new_class_fragment");

 */