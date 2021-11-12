package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.List;

import edu.illinois.cs465.pixelbyte.R;

class ListElem {
    TextView className;
    TextView letterGrade;
    TextView numberGrade;
    Button getHelp;
}



public class ClassListAdapter extends ArrayAdapter<ClassData> {
    public ClassListAdapter(Context context, List<ClassData> objects) {
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
        return LayoutInflater.from(getContext()).inflate(R.layout.class_list_element, null);
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

        TextView classLabel = (TextView) convertView.findViewById(R.id.classname);
        if (classLabel != null && classItem.className != null && classItem.className.length() > 0) {
            classLabel.setText(classItem.className);
        }

        TextView letterLabel = (TextView) convertView.findViewById(R.id.lettergrade);
        if (letterLabel != null && classItem.letterGrade != null && classItem.letterGrade.length() > 0) {
            letterLabel.setText(classItem.letterGrade);
        }

        TextView numLabel = (TextView) convertView.findViewById(R.id.numgrade);
        if (numLabel != null && classItem.numberGrade != 0) {
            numLabel.setText(makePercent(classItem.numberGrade));
        }

        CardView coloredPortion = (CardView) convertView.findViewById(R.id.colored_portion);
        if (coloredPortion != null && classItem.color != 0) {
            coloredPortion.setBackgroundColor(classItem.color);
        }

        TextView getHelpButton = (TextView) convertView.findViewById(R.id.get_help);
        if (getHelpButton != null && (classItem.goal != 0 && classItem.numberGrade >= classItem.goal)) {
            getHelpButton.setHeight(0);
            getHelpButton.setWidth(0);
        }

        return convertView;
    }
}