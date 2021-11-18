package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;

import edu.illinois.cs465.pixelbyte.ClassList.ClassData;

public class ClassActivity extends AppCompatActivity {
    BottomSheetDialogFragment openDialog;
    ClassData classData_;

    ArrayList<String> catList = new ArrayList<>();
    HashMap<String, String> weightMap = new HashMap<>();

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

        classData_ = ClassData.extract(getIntent());

        this.setTitle(classData_.className_);

        TextView grade = (TextView) findViewById(R.id.numgrade);
        grade.setText(classData_.makeGradeString());

        TextView goal = (TextView) findViewById(R.id.goal);
        goal.setText(classData_.makeGoalString());
    }
}