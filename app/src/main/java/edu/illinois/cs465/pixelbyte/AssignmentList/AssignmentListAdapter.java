package edu.illinois.cs465.pixelbyte.AssignmentList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassStructures.Assignment;
import edu.illinois.cs465.pixelbyte.R;

public class AssignmentListAdapter extends ArrayAdapter<Assignment> {
    public AssignmentListAdapter(Context context, List<Assignment> categories) {
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
        Assignment assignment = getItem(position);

        // If there isn't a view, don't update it
        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        TextView nameTag = (TextView) convertView.findViewById(R.id.category_name);
        if (nameTag != null) {
            nameTag.setText(assignment.name_);
        }

        TextView percentTag = (TextView) convertView.findViewById(R.id.category_grade);
        if (percentTag != null) {
            percentTag.setText(assignment.makeGrade());
        }


        // Return the completed view to render on screen
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.class_category_list_item, null);
    }
}
