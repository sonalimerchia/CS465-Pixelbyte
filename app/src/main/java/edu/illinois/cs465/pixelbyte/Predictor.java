package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.ClassStructures.PredictorCategory;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.PredictorList.PredictorCategoryAdapter;

public class Predictor extends AppCompatActivity {

    ClassData classData_;
    List<PredictorCategory> predictionCategories_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictor);

        classData_ = ClassData.extract(getIntent().getExtras());
        predictionCategories_ = makeCategories(classData_.getCategories());

        PredictorCategoryAdapter adapter = new PredictorCategoryAdapter(this, predictionCategories_);
        ListView categoryItems = (ListView) findViewById(R.id.predictor_list);
        categoryItems.setAdapter(adapter);

        setupPredictButton();

        setTitle("Predictor");
    }

    private void setupPredictButton() {
        Button button = findViewById(R.id.predict_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPredictionDialog();
            }
        });
    }

    private void openPredictionDialog() {
        double grade = 0;

        for (PredictorCategory pc : predictionCategories_) {
            grade += pc.getContribution();
        }

        DialogFragment df = new GradePrediction(grade);
        df.show(getSupportFragmentManager(), "Prediction");
    }

    private List<PredictorCategory> makeCategories(List<TemplateCategory> categories) {
        List<PredictorCategory> list = new ArrayList<>();
        for (TemplateCategory tc : categories) {
            list.add(new PredictorCategory(tc));
        }

        return list;
    }
}