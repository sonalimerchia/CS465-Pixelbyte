package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        try {
            FileInputStream inputStream = openFileInput("Class.cf");
            Scanner s = new Scanner(inputStream);
            classname = s.nextLine();
            goalStr = s.nextLine();
            helpStr = s.nextLine();
            int numCategories = s.nextInt();
            for (int x = 0; x < numCategories; x++) {
                //Read a category
                s.nextLine(); s.nextLine();

                String catName = s.nextLine();
                catList.add(catName);
                String weight = s.nextLine();

                weightMap.put(catName, weight);
                String scheme = s.nextLine();
                int numAssignments = s.nextInt();
                for (int y = 0; y < numAssignments; y++) {
                    String assignmentStr = s.nextLine();
                }
            }
            inputStream.close();
        } catch (Exception e) {}
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

        String className = getIntent().getExtras().getString("ClassName");
        readIn();
        goalView.setText(goalStr);




        for (String eachStr : catList) {
            Button b = new Button(this);
            b.setText(eachStr);
            assignmentLayout.addView(b);
            /*
            Modify this code to launch the detailed view of a category page
            Intent intent = new Intent(this, .class);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            */
        }

    }
}