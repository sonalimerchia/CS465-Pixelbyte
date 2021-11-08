package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.illinois.cs465.pixelbyte.R;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

class ListElem {
    TextView className;
    TextView letterGrade;
    TextView numberGrade;
    Button getHelp;
}

class Class {
    String className;
    String letterGrade;
    String numberGrade;

    public Class(String classname, String let, String num) {
        className = classname;
        letterGrade = let;
        numberGrade = num;
    }
}

public class ClassListAdapter extends ArrayAdapter<String> {
    public ClassListAdapter(Context context, List<String> objects) {
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
        return LayoutInflater.from(getContext()).inflate(R.layout.list_elem, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String className = getItem(position);

        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
        }

        TextView classLabel = (TextView) convertView.findViewById(R.id.classname);
        if (classLabel != null && className != null && className.length() > 0) {
            classLabel.setText(className);
        }

        return convertView;
    }
}