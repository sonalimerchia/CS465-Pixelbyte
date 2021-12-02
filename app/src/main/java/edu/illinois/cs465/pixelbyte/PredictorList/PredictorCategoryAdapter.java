package edu.illinois.cs465.pixelbyte.PredictorList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.R;

public class PredictorCategoryAdapter extends ArrayAdapter<TemplateCategory> {

    public PredictorCategoryAdapter(Context context, List<TemplateCategory> objects) {
        super(context, 0, objects);
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
        TemplateCategory category = getItem(position);

        // If there isn't a view, don't update it
        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        TextView title = convertView.findViewById(R.id.category_name);
        if (title != null) {
            title.setText(category.name_);
        }

        TextView currentGrade = convertView.findViewById(R.id.current_grade);
        TextView predictGrade = convertView.findViewById(R.id.predicted_grade);
        if (currentGrade != null) {
            currentGrade.setText(category.getGrade());
        }
        if (predictGrade != null) {
            predictGrade.setText(category.getGrade());
        }

        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.predictor_list_element, null);
    }
}
