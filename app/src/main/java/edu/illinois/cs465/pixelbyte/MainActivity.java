package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import edu.illinois.cs465.pixelbyte.ClassList.ClassData;
import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
    private ListView classList;
    private String currentSemester;
    private ArrayList<ClassData> classes; //each elem = class name, letter, number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up buttons at bottom of screen
        Button newClassButton = (Button) findViewById(R.id.open_template);
        newClassButton.setOnClickListener(this);
        Intent intent = new Intent(this, CreateClass.class);
        newClassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        /*
        Button profileButton = (Button) findViewById(R.id.open_profile);
        profileButton.setOnClickListener(this);
        Intent intentProfile = new Intent(this, ProfileActivity.class);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intentProfile);
            }
        });
        */
         



        //stuff to grab from other screens TODO: connect it all
        currentSemester = "Spring 2022";
        //classes = new ArrayList<Class>(Arrays.asList(new Class("CS 125", "A", "95.5%")));
        classes = new ArrayList<ClassData>();
        classes.add(new ClassData("CS 125", "A", "95.5%"));
        classes.add(new ClassData("CS 465", "C+", "78.9%"));

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

    public void clickedButton(View v) {
        Button b = (Button) v;
        ConstraintLayout s = (ConstraintLayout) b.getParent();
        TextView t = (TextView) s.getChildAt(3);
        String clickedHelpText = (String) t.getText();
    }


    public void clickedClass(View v) {
        ConstraintLayout s = (ConstraintLayout) v;
        TextView t = (TextView) s.getChildAt(3);
        Intent intent = new Intent(this, ClassActivity.class);
        intent.putExtra("ClassName", t.getText());
        startActivity(intent);
    }
}