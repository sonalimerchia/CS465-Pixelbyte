package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClassActivity extends AppCompatActivity {
    TextView percentageView;
    TextView goalView;
    LinearLayout assignmentLayout;
    Button helpButton;
    Button weightsButton;
    Button predictButton;
    BottomSheetDialogFragment openDialog;


    String goalStr;
    String helpStr;
    String percentageStr;

    String classname;

    ArrayList<String> catList = new ArrayList<>();
    HashMap<String, String> weightMap = new HashMap<>();

    private void readIn() {
        switch (classname) {
            case "CS 125":
                goalStr = "91%";
                helpStr = "";
                percentageStr = "90%";
                catList.add("Quizzes");
                catList.add("Tests");
                catList.add("Homeworks");
                break;
            case "CS 126":
                goalStr = "90%";
                helpStr = "";
                percentageStr = "92.4%";
                catList.add("Projects");
                catList.add("Tests");
                break;
            case "CS 225":
                goalStr = "88%";
                helpStr = "";
                percentageStr = "84.2%";
                catList.add("MPs");
                catList.add("Tests");
                catList.add("Homeworks");
                break;
        }
        goalView.setText(goalStr);
        percentageView.setText(percentageStr);

    }

    public void clickedNew(View view) {
        openDialog("New Category");
    }

    private void openDialog(String bottomSheetName) {
        openDialog = new AddCategory();
        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        goalView = (TextView) findViewById(R.id.GoalTextBox);
        percentageView = (TextView) findViewById(R.id.PercentageTextBox);
        assignmentLayout = (LinearLayout) findViewById(R.id.AssignmentLayout);
        helpButton = (Button) findViewById(R.id.helpButton);
        weightsButton = (Button) findViewById(R.id.weightsButton);

        classname = getIntent().getExtras().getString("ClassName");
        this.setTitle(classname);
        readIn();
        for (String eachStr : catList) {
            Button b = new Button(this);
            b.setText(eachStr);
            assignmentLayout.addView(b);
            Intent intent = new Intent(this, AssignmentViewActivity.class);
            intent.putExtra("ClassName", classname);
            intent.putExtra("Category", eachStr);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }

        // Predictor
        predictButton = (Button) findViewById(R.id.predictor);
        Intent predictIntent = new Intent(this, Predictor.class);
        predictIntent.putExtra("Categories", catList);
        predictIntent.putExtra("Goal", goalStr);
        predictIntent.putExtra("Percentage", percentageStr);
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(predictIntent);
            }
        });
    }
}