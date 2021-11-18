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

public class Predictor extends AppCompatActivity {

    Toolbar toolbar;

    TextView result;
    EditText input;
    TextView category;
    Button calculate;
    CarouselView carouselView;
    int[] categoryImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

    ArrayList<String> posToCategory;
    double goal;
    double percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictor);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);S
        posToCategory = getIntent().getExtras().getStringArrayList("Categories");
        String goalStr = getIntent().getExtras().getString("Goal");
        String percentageStr = getIntent().getExtras().getString("Percentage");
        goal =  Double.parseDouble(goalStr.substring(0, goalStr.length()-1));
        percentage = Double.parseDouble(percentageStr.substring(0,percentageStr.length()-1));

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        result = findViewById(R.id.result);
        input = findViewById(R.id.input);
        category = findViewById(R.id.category);
        calculate = findViewById(R.id.calculate);
        input.setText(String.valueOf(goal));

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(posToCategory.size());
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(imageClickListener);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = String.format("%.1f", getCurrAvgGrade()) + "%";
                result.setText(res);
            }
        });

        carouselView.performClick();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(categoryImages[position%4]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    };

    ImageClickListener imageClickListener = new ImageClickListener() {
        @Override
        public void onClick(int position) {
            String txt = "Category: " + posToCategory.get(position);
            category.setText(txt);
            String res = String.format("%.1f", getCurrAvgGrade(position)) + "%";
            result.setText(res);
        }
    };

    private double getCurrAvgGrade() {
        double tempSum = 0;
        for (int i = 0; i < posToCategory.size(); i++) {
            if (0 != i) {
                // TODO: switch with avg
                tempSum += percentage;
            }
        }
        try {
            double goal = Double.parseDouble(input.getText().toString());
            double predicted = goal * posToCategory.size() - tempSum;
            return predicted;
        } catch (NumberFormatException e) {
            // TODO: switch with avg
            return percentage;
        }
    }

    private double getCurrAvgGrade(int position) {
        double[] avg = {90, 80, 70, 60};
        double tempSum = 0;
        for (int i = 0; i < posToCategory.size(); i++) {
            if (position != i) {
                // TODO: switch with avg
                tempSum += percentage;
            }
        }
        try {
            double goal = Double.parseDouble(input.getText().toString());
            double predicted = goal * posToCategory.size() - tempSum;
            return predicted;
        } catch (NumberFormatException e) {
            // TODO: switch with avg
            return percentage;
        }
    }

    private double getGradeEstimate(int position) {
        return 0;
    }

}