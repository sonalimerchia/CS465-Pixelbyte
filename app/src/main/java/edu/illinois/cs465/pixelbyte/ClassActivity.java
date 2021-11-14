package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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


    String goalStr;
    String helpStr;

    String classname;

    ArrayList<String> catList = new ArrayList<>();
    HashMap<String, String> weightMap = new HashMap<>();

    private void readIn() {
        switch (classname) {
            case "CS 125":
                goalStr = "91%";
                helpStr = "";
                percentageView.setText("90%");
                catList.add("Quizzes");
                catList.add("Tests");
                catList.add("Homeworks");
                break;
            case "CS 126":
                goalStr = "90%";
                helpStr = "";
                percentageView.setText("92.4%");
                catList.add("Projects");
                catList.add("Tests");
                break;
            case "CS 225":
                goalStr = "88%";
                helpStr = "";
                percentageView.setText("84.2%");
                catList.add("MPs");
                catList.add("Tests");
                catList.add("Homeworks");
                break;
        }
        goalView.setText(goalStr);

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

    }
}