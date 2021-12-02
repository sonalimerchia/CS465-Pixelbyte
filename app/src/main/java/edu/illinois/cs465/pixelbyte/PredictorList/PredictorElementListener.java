package edu.illinois.cs465.pixelbyte.PredictorList;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class PredictorElementListener implements Slider.OnChangeListener, TextWatcher {
    TextView predictionDisplay_;
    TemplateCategory category_;
    List<TemplateCategory> objects_;

    int position_;
    double currentEarned_;
    double currentTotal_;

    public PredictorElementListener(TemplateCategory tc, TextView pd, EditText pointInput, Slider predict, int pos, List<TemplateCategory> listRef) {
        predictionDisplay_ = pd;
        category_ = tc;
        category_.remainingPoints_ = parseDouble(pointInput.getText().toString());
        category_.projectedPercentage_ = predict.getValue();
        objects_ = listRef;
        position_ = pos;

        currentTotal_ = 0;
        currentEarned_ = 0;

        for (Assignment a : tc.getAssignments()) {
            currentEarned_ += a.earnedPoints_;
            currentTotal_ += a.maxPoints_;
        }

        updateScore();
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
        String result = editable.toString();

        category_.remainingPoints_ = parseDouble(result);
        updateScore();
    }

    private double parseDouble(String string) {
        if (string.endsWith(".")) {
            string = string.substring(0, string.length() - 1);
        }

        try {
            return Double.parseDouble(string);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        category_.projectedPercentage_ = value;

        if (category_.remainingPoints_ != 0) {
            updateScore();
        }
    }

    private void updateScore() {
        double toAdd = category_.remainingPoints_ * category_.projectedPercentage_ / 100;
        toAdd = (toAdd + currentEarned_) / (category_.remainingPoints_ + currentTotal_);
        String result = (int)(toAdd * 10000)/100.0 + "%";
        predictionDisplay_.setText(result);

        objects_.set(position_, category_);
    }
}
