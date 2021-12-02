package edu.illinois.cs465.pixelbyte.PredictorList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.PredictorCategory;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.R;

public class PredictorCategoryAdapter extends ArrayAdapter<PredictorCategory> {
    List<PredictorCategory> data_;

    public PredictorCategoryAdapter(Context context, List<PredictorCategory> objects) {
        super(context, 0, objects);

        data_ = objects;
    }

    // Return an integer representing the type by fetching the enum type ordinal
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    // Total number of types is the number of enum values
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the item at the given position
        PredictorCategory category = getItem(position);

        // If there isn't a view, don't update it
        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        TextView title = convertView.findViewById(R.id.category_name);
        if (title != null) {
            title.setText(category.getName());
        }

        TextView currentGrade = convertView.findViewById(R.id.current_grade);
        TextView predictGrade = convertView.findViewById(R.id.predicted_grade);
        if (currentGrade != null) {
            currentGrade.setText(category.getGrade());
        }
        if (predictGrade != null) {
            predictGrade.setText(category.getProjection());
        }

        EditText numAssignments = convertView.findViewById(R.id.remaining_points);
        Slider slider = convertView.findViewById(R.id.predictedScore);

        numAssignments.addTextChangedListener(new PredictorElementListener(category, this, position));
        slider.addOnChangeListener(new PredictorElementListener(category, this, position));

        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.predictor_list_element, null);
    }

    public void updateItem(int position, PredictorCategory newVersion) {
        data_.set(position, newVersion);
    }

}
