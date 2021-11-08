package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/* This is the first activity page to create a Class Object.

TODO: Return a Class Object on completion. (Keep user input between CreateClass screens).
 Update names/buttons to match other screens. Navigation.
 Convert this activity into a Dialog Activity to partially cover Home Screen.
 */
public class CreateClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    /* TODO: Make a toast or something appear on Home Page after a class is successfully created. */
    public static final String CREATE_CLASS_RETURN_MSG = "CREATE_CLASS_RETURN_MSG";

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

    private Spinner courseDeptSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        //TextViews
        addClassTitle = (TextView) findViewById(R.id.add_course_title);
        courseNameLabel = (TextView) findViewById(R.id.course_name_label);
        courseColorLabel = (TextView) findViewById(R.id.course_color_label);
        courseDeptLabel = (TextView) findViewById(R.id.course_department_label);

        addClassTitle.setText(R.string.activity_title_add_course);
        courseNameLabel.setText(R.string.course_name_label);
        courseColorLabel.setText(R.string.course_color_label);
        courseDeptLabel.setText(R.string.course_department_label);

        //EditText
        courseNameInput = (EditText) findViewById(R.id.course_edit_name);
        courseNameInput.setAllCaps(false);
        courseNameInput.setHint(R.string.course_name_hint);

        //Color Buttons
        redButton = (Button) findViewById(R.id.red_button);
        greenButton = (Button) findViewById(R.id.green_button);
        blueButton = (Button) findViewById(R.id.blue_button);

        //TODO: Custom Color selector button.

        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);

        //Navigation Buttons
        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(this);

        //Spinner (Drop-down menu)
        courseDeptSpinner = (Spinner) findViewById(R.id.course_dept_spinner);
        courseDeptSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_departments_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        courseDeptSpinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /* TODO: Button Navigation + theme changing */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.red_button) {
            // Change Class theme to red
        }
        if (v.getId() == R.id.green_button) {
            // Change Class theme to green
        }
        if (v.getId() == R.id.blue_button) {
            // Change Class theme to bllue
        }

        if (v.getId() == R.id.next_button) {
            // Go to next fragment/activity
        }
    }
}