package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.illinois.cs465.pixelbyte.CategoryList.ClassCategoryAdapter;
import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;

public class ClassActivity extends AppCompatActivity {
    BottomSheetDialogFragment openDialog;
    ClassCategoryAdapter adapter;
    ClassData classData_;

    private void openDialog(String bottomSheetName) {
        openDialog = new AddAssignment(classData_.getCategories());
        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        Bundle extras = getIntent().getExtras();
        classData_ = ClassData.extract(extras);

        this.setTitle(classData_.className_);

        // Changes color of status bar [uses or overrides deprecated API here?]
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(classData_.color_);

        //Changes color of app/action bar
        ActionBar actionBar;
        actionBar = this.getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(classData_.color_);
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        updateSummary();

        CardView addButton = (CardView) findViewById(R.id.create_assignment);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog("Add Assignment");
            }
        });

        // Create adapter to interpret data
        ListView categories = (ListView) findViewById(R.id.category_list);
        adapter = new ClassCategoryAdapter(this, classData_.getCategories());

        // Apply adapter to list
        categories.setAdapter(adapter);

        // Create predictor
        Button predictButton = (Button) findViewById(R.id.predict_button);
        Intent predictIntent = new Intent(this, Predictor.class);
        classData_.addToIntent(predictIntent);
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(predictIntent);
            }
        });

        //Change color of View Weight and Predict Grades Buttons
        Button weightButton = (Button) findViewById(R.id.weightsButton);
        if (classData_.color_ == 0xFFEE5E5E) {
            weightButton.setBackgroundColor(getResources().getColor(R.color.salmon_gradient));
            predictButton.setBackgroundColor(getResources().getColor(R.color.salmon_gradient));
        } else if (classData_.color_ == 0xFFFFD125) {
            weightButton.setBackgroundColor(getResources().getColor(R.color.dark_yellow_gradient));
            predictButton.setBackgroundColor(getResources().getColor(R.color.dark_yellow_gradient));
        } else if (classData_.color_ == 0xFFBFD46D) {
            weightButton.setBackgroundColor(getResources().getColor(R.color.citron_gradient));
            predictButton.setBackgroundColor(getResources().getColor(R.color.citron_gradient));
        } else {
            weightButton.setBackgroundColor(getResources().getColor(R.color.periwinkle_gradient));
            predictButton.setBackgroundColor(getResources().getColor(R.color.periwinkle_gradient));
        }
    }

    public void addAssignment(Assignment a, String category) {
        classData_.addAssignment(category, a);
        adapter.notifyDataSetChanged();
    }

    public void updateSummary() {
        TextView grade = (TextView) findViewById(R.id.numgrade);
        grade.setText(classData_.makeGradeString());

        TextView goal = (TextView) findViewById(R.id.goal);
        goal.setText(classData_.makeGoalString());
    }
}