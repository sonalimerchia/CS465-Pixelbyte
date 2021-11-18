package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Intent;
import android.view.View;

import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassActivity;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class ClassElementClickListener implements View.OnClickListener {
    ClassData classData_;

    public ClassElementClickListener(ClassData cd) {
        classData_ = cd;
    }

    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ClassActivity.class);
        classData_.addToIntent(intent);
        v.getContext().startActivity(intent);
    }
}