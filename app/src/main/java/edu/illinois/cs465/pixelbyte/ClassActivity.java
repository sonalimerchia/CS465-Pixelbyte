package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.CategoryList.ClassCategoryAdapter;
import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;

public class ClassActivity extends AppCompatActivity {
    BottomSheetDialogFragment openDialog;
    ClassData classData_;

    private void openDialog(String bottomSheetName) {
        openDialog = new AddAssignment(classData_.categories_);
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

        CardView addButton = (CardView) findViewById(R.id.create_assignment);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog("Add Assignment");
            }
        });

        // Create adapter to interpret data
        ListView categories = (ListView) findViewById(R.id.category_list);
        ClassCategoryAdapter arr = new ClassCategoryAdapter(this, classData_.categories_);

        // Apply adapter to list
        categories.setAdapter(arr);

        System.out.println(classData_.categories_.get(0).enteredAssignments_.size());
    }
}