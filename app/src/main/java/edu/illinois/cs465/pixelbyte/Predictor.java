package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

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

        setTitle("Predictor");
    }

    private List<PredictorCategory> makeCategories(List<TemplateCategory> categories) {
        List<PredictorCategory> list = new ArrayList<>();
        for (TemplateCategory tc : categories) {
            list.add(new PredictorCategory(tc));
        }

        return list;
    }

    private double getGradeEstimate(int position) {
        return 0;
    }

}