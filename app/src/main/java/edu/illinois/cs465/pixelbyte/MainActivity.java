package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
=======

>>>>>>> 5754577 (move classes out of main)
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.List;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
=======

import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;
>>>>>>> 5754577 (move classes out of main)

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
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
        classes = new ArrayList<String>(Arrays.asList("CS 125", "CS 126", "CS 225", "CS 233"));

        getSupportActionBar().setTitle(currentSemester);

        //setup listview
        TextView noClasses = (TextView) this.findViewById(R.id.noclasses);
        if (classes.size() == 0) {
            noClasses.setVisibility(View.VISIBLE);
        } else {
            noClasses.setVisibility(View.INVISIBLE);
            ListView classList = (ListView) findViewById(R.id.classlist);
            ClassListAdapter adapter = new ClassListAdapter(this, R.layout.list_elem, classes);
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

        //setup buttons
        /*
        Button addBtn = (Button)findViewById(R.id.addClassButton);
        Button profileBtn = (Button)findViewById(R.id.profileButton);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, profileActivity.class));
            }
*/

    public void onClick(View v) {
        if (v.getId() == R.id.open_template) {
            // Open new template page
            openDialog(BottomSheetCodes.NewTemplatePreview, "Edit Template");
        } else if (v.getId() == R.id.add_category) {
            // Open add category sheet
            openDialog(BottomSheetCodes.AddCategory, "Add Category");
        }
    }

    //list adapter code based off code from https://github.com/CincyAndroiDeveloper/Android-Tutorials

}