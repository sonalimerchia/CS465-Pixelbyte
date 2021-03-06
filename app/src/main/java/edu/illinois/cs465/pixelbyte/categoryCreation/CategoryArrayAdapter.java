package edu.illinois.cs465.pixelbyte.categoryCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.R;

public class CategoryArrayAdapter extends ArrayAdapter<TemplateCategory> {
    int viewType_;

    public CategoryArrayAdapter(Context context, List<TemplateCategory> categories, int id) {
        super(context, 0, categories);
        viewType_ = id;
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

        // Update all the labels of the list item to match the category
        TextView nameLabel = (TextView) convertView.findViewById(R.id.category_name);
        TextView weightLabel = (TextView) convertView.findViewById(R.id.category_weight);
        TextView dropLabel = (TextView) convertView.findViewById(R.id.category_drops);
        TextView assignmentLabel = (TextView) convertView.findViewById(R.id.category_assigments);

        // Update name label
        if (nameLabel != null) {
            nameLabel.setText(category.name_);
        }

        // Update weight label
        if (weightLabel != null) {
            double weight = Math.round(category.weight_ * 100)/100.0;
            weightLabel.setText(""+weight+"%");
        }

        // Update number of drops label
        if (dropLabel != null && category.drops_ != 0) {
            String result = ""+category.drops_ +" drop"+(category.drops_ < 2 ? "" : "s");
            dropLabel.setText(result);
        } else {
            dropLabel.setHeight(0);
        }

        // Update number of assignments label
        if (assignmentLabel != null && category.assignments_ != 0) {
            String result = ""+category.assignments_ +" assignment"+(category.assignments_ < 2 ? "" : "s");
            assignmentLabel.setText(result);
        } else {
            assignmentLabel.setHeight(0);
        }

        ImageView deleteButton = (ImageView) convertView.findViewById(R.id.delete_category);
        if (deleteButton != null) {
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(category);
                }
            });
        }

        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(viewType_, null);
    }
}