package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import edu.illinois.cs465.pixelbyte.ClassActivity;
import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.TutoringInformationFragment;

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