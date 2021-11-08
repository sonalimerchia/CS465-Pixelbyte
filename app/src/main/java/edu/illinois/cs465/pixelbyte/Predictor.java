package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class Predictor extends AppCompatActivity {

    Toolbar toolbar;

    TextView result;
    EditText input;
    TextView category;
    CarouselView carouselView;
    int[] categoryImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    String[] posToCategory = {"Homework", "MPs", "Participation", "Exam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);S

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        result = findViewById(R.id.result);
        input = findViewById(R.id.input);
        category = findViewById(R.id.category);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(categoryImages.length);
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(imageClickListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(categoryImages[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    };

    ImageClickListener imageClickListener = new ImageClickListener() {
        @Override
        public void onClick(int position) {
            String txt = "Category: " + posToCategory[position];
            category.setText(txt);
            result.setText(String.valueOf(getCurrAvgGrade(position)));
        }
    };

    private double getCurrAvgGrade(int position) {
        double[] avg = {90, 80, 70, 60};
        double tempSum = 0;
        for (int i = 0; i < categoryImages.length; i++) {
            if (position != i) {
                tempSum += avg[i];
            }
        }
        try {
            double goal = Double.parseDouble(input.getText().toString());
            double predicted = goal * categoryImages.length - tempSum;
            return predicted;
        } catch (NumberFormatException e) {
            return avg[position];
        }
    }

    private double getGradeEstimate(int position) {
        return 0;
    }

}