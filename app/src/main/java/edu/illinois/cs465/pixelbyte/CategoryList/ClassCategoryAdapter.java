package edu.illinois.cs465.pixelbyte.CategoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.R;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class ClassCategoryAdapter extends ArrayAdapter<TemplateCategory> {
    public ClassCategoryAdapter(Context context, List<TemplateCategory> categories) {
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
        // Get the item at the given position
        TemplateCategory category = getItem(position);

        // If there isn't a view, don't update it
        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        convertView.setOnClickListener(new ClassCategoryElementClickListener(category));

        TextView nameTag = (TextView) convertView.findViewById(R.id.category_name);
        if (nameTag != null) {
            nameTag.setText(category.name_);
        }

        TextView percentTag = (TextView) convertView.findViewById(R.id.category_grade);
        if (percentTag != null) {
            if (category.enteredAssignments_.size() == 0) {
                percentTag.setText("No Data");
            } else {
                percentTag.setText(category.getGrade());
            }
        }


        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.class_category_list_item, null);
    }
}