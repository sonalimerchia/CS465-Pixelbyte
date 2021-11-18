package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Intent;
import android.view.View;

import edu.illinois.cs465.pixelbyte.ClassActivity;

public class ClassElementClickListener implements View.OnClickListener {
    ClassData classData_;

    public ClassElementClickListener(ClassData cd) {
        classData_ = cd;
    }

    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ClassActivity.class);
        intent.putExtra("ClassName", classData_.className_);
        v.getContext().startActivity(intent);
    }
}
