package edu.illinois.cs465.pixelbyte.categoryCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.illinois.cs465.pixelbyte.R;

public class CategoryArrayAdapter extends ArrayAdapter<TemplateCategory> {
    public CategoryArrayAdapter(Context context, TemplateCategory[] categories) {
        super(context, 0, categories);
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
        TemplateCategory category = getItem(position);

        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        // Lookup view for data population
        TextView nameLabel = (TextView) convertView.findViewById(R.id.category_name);
        TextView weightLabel = (TextView) convertView.findViewById(R.id.category_weight);
        TextView dropLabel = (TextView) convertView.findViewById(R.id.category_drops);
        TextView assignmentLabel = (TextView) convertView.findViewById(R.id.category_assigments);

        if (nameLabel != null) {
            nameLabel.setText(category.name);
        }

        if (weightLabel != null) {
            double weight = Math.round(category.weight * 100)/100.0;
            weightLabel.setText(""+weight+"%");
        }

        if (dropLabel != null && category.drops != 0) {
            String result = ""+category.drops+" drop"+(category.drops < 2 ? "" : "s");
            dropLabel.setText(result);
        } else {
            dropLabel.setHeight(0);
        }

        if (assignmentLabel != null && category.assignments != 0) {
            String result = ""+category.assignments+" assignment"+(category.assignments < 2 ? "" : "s");
            assignmentLabel.setText(result);
        } else {
            assignmentLabel.setHeight(0);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.creating_template_item, null);
    }
}