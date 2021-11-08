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
    private int layout;
    private List<String> objs; //TODO: later figure out how to pass actual class objects in here
    public ClassListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        objs = objects;
        layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListElem mainElem = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);

            ListElem tempElem = new ListElem();
            tempElem.className = (TextView) convertView.findViewById(R.id.classname);
            tempElem.letterGrade = (TextView) convertView.findViewById(R.id.lettergrade);
            tempElem.numberGrade = (TextView) convertView.findViewById(R.id.numgrade);
            tempElem.getHelp = (Button) convertView.findViewById(R.id.gethelp);
            convertView.setTag(tempElem);
        }
        mainElem = (ListElem) convertView.getTag();

        mainElem.getHelp.setOnClickListener(v -> {
            //TODO: go to gethelp page for the class at getItem(position)
        });

        mainElem.className.setText(getItem(position));
        //mainViewholder.letterGrade.setText(getItem(position));
        //mainViewholder.numberGrade.setText(getItem(position));


        return convertView;
    }
}