package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
    private ListView classList;
    private String currentSemester;
    private ArrayList<String> classes; //each elem = class name, letter, number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openDialogButton = (Button) findViewById(R.id.open_template);
        openDialogButton.setOnClickListener(this);

        openDialogButton = (Button) findViewById((R.id.add_category));
        openDialogButton.setOnClickListener(this);

        //stuff to grab from other screens TODO: connect it all
        currentSemester = "Spring 2022";
        //classes = new ArrayList<Class>(Arrays.asList(new Class("CS 125", "A", "95.5%")));
        classes = new ArrayList<String>(Arrays.asList("CS 125", "CS 126", "CS 225"));

        // Create adapter to interpret data

        getSupportActionBar().setTitle(currentSemester);

        //setup listview
        classList = (ListView) findViewById(R.id.classlist);
        TextView noClasses = (TextView) this.findViewById(R.id.noclasses);

        if (classes.size() == 0) {
            noClasses.setVisibility(View.VISIBLE);
            classList.setVisibility(View.INVISIBLE);
        } else {
            classList.setVisibility(View.VISIBLE);
            noClasses.setVisibility(View.INVISIBLE);

            ClassListAdapter adapter = new ClassListAdapter(this, classes);
            classList.setAdapter(adapter);
        }
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

    public void onClick(View v) {
        if (v.getId() == R.id.open_template) {
            // Open new template page
            openDialog(BottomSheetCodes.NewTemplatePreview, "Edit Template");
        } else if (v.getId() == R.id.add_category) {
            // Open add category sheet
            openDialog(BottomSheetCodes.AddCategory, "Add Category");
        }
    }
}