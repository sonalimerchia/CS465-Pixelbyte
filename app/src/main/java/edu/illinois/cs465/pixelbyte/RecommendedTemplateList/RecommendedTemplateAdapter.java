package edu.illinois.cs465.pixelbyte.RecommendedTemplateList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassList.ClassData;
import edu.illinois.cs465.pixelbyte.R;

public class RecommendedTemplateAdapter extends ArrayAdapter<ClassData> {
    public RecommendedTemplateAdapter(Context context, List<ClassData> objects) {
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

    private View getInflatedLayoutForType(int type) {
        return LayoutInflater.from(getContext()).inflate(R.layout.recommended_template_item, null);
    }

    private String makePercent(double percentage) {
        return (Math.round(percentage * 100) / 100.0) + "%";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassData classItem = getItem(position);

        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        TextView classLabel = (TextView) convertView.findViewById(R.id.template_name);
        if (classLabel != null && classItem.className_ != null && classItem.className_.length() > 0) {
            classLabel.setText(classItem.className_);
        }

        if (convertView != null && classItem.color_ != 0) {
            convertView.setClipToOutline(true);
        }

        return convertView;
    }
}
