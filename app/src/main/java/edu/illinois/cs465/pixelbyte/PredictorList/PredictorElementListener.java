package edu.illinois.cs465.pixelbyte.PredictorList;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.ClassStructures.PredictorCategory;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class PredictorElementListener implements Slider.OnChangeListener, TextWatcher {
    PredictorCategory category_;
    PredictorCategoryAdapter adapter_;

    int position_;

    public PredictorElementListener(PredictorCategory category, PredictorCategoryAdapter parent, int position) {
        category_ = category;
        adapter_ = parent;
        position_ = position;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Do Nothing
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        double result = parseDouble(editable.toString()) ;

        if (result != -1) {
            category_.setRemainingPoints(result);
        }
    }

    private double parseDouble(String string) {
        if (string.endsWith(".")) {
            string = string.substring(0, string.length() - 1);
        }

        try {
            return Double.parseDouble(string);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        category_.setProjectedPercentage(value);
        adapter_.updateItem(position_, category_);
    }
}
