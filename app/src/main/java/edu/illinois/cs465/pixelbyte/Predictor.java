package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class Predictor extends AppCompatActivity {

    ClassData classData_;
    List<String> categoryNames_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictor);

        classData_ = ClassData.extract(getIntent().getExtras());
        categoryNames_ = new ArrayList<>();
        for (TemplateCategory tc : classData_.categories_) {
            categoryNames_.add(tc.name_);
        }

        setTitle("Predictor");
    }



    private double getGradeEstimate(int position) {
        return 0;
    }

}