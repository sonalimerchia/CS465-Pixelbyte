package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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

    private void openDialog(BottomSheetCodes code, String bottomSheetName) {
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
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.create_class) {
            openDialog(BottomSheetCodes.CreateClass, "Create Class");
            return;
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

//        try {
//            FileOutputStream outputStream = openFileOutput("Class.cf", Context.MODE_PRIVATE);
//            String str = "CS 465\n" +
//                    "90.0%\n" +
//                    "Help can be found on canvas.illinois.edu\n" +
//                    "3\n" +
//                    "\n" +
//                    "Quizzes\n" +
//                    "30%\n" +
//                    "Scheme...\n" +
//                    "3\n" +
//                    "Quiz_1\t90\n" +
//                    "Quiz_2\t98\n" +
//                    "Quiz_3\t92\n" +
//                    "\n" +
//                    "Tests\n" +
//                    "40%\n" +
//                    "Scheme...\n" +
//                    "2\n" +
//                    "Test_1\t90\n" +
//                    "Test_2\t98\n" +
//                    "\n" +
//                    "Projects\n" +
//                    "30%\n" +
//                    "Scheme...\n" +
//                    "1\n" +
//                    "Main_Project\t85";
//            outputStream.write(str.getBytes());
//            outputStream.flush();
//            outputStream.close();
//        } catch (Exception e) {}
//
//        Intent intent = new Intent(this, ClassActivity.class);
//        intent.putExtra("ClassName", "CS 465");
//        classButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                startActivity(intent);
//            }
//        });
    }
}