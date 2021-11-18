package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.ClassList.ClassData;
import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
    private String currentSemester;
    private ArrayList<ClassData> classes; //each elem = class name, letter, number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView createClassButton = (CardView) findViewById(R.id.create_class);
        createClassButton.setOnClickListener(this);

        //stuff to grab from other screens TODO: connect it all
        currentSemester = "Spring 2022";

        createClassList();
    }

    private void createClassList() {
        classes = new ArrayList<>();
        classes.add(new ClassData("CS 125", "A", 95.5, 0xffffff00, 90.0));
        classes.add(new ClassData("CS 465", "C+", 78.9, 0xff00ff00, 90.0));
        classes.add(new ClassData("CS 233", "B-", 83.9, 0xff00ffff, 80.0));

        // Create adapter to interpret data

        getSupportActionBar().setTitle(currentSemester);

        //setup listview
        ListView classList = (ListView) findViewById(R.id.classlist);
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

    public void openDialog(BottomSheetCodes code, String bottomSheetName) {
        if (openDialog != null && openDialog.isVisible()) {
            openDialog.onDestroyView();
        }

        switch (code) {
            case CreateClass:
                openDialog = new CreateClass();
                break;
            case FindTemplate:
                openDialog = new FindTemplate();
                break;
            case NewTemplatePreview:
                openDialog = new CreateNewTemplate();
                break;
            case AddCategory:
                openDialog = new AddCategory();
                break;
            case PreviewTemplate:
                openDialog = new PreviewTemplate(bottomSheetName);
                break;
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.create_class) {
            openDialog(BottomSheetCodes.CreateClass, "Create Class");
            return;
        }
    }
}