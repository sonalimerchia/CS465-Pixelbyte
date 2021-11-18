package edu.illinois.cs465.pixelbyte.RecommendedTemplateList;

import android.view.View;

import edu.illinois.cs465.pixelbyte.BottomSheetCodes;
import edu.illinois.cs465.pixelbyte.MainActivity;

public class RecommendationElementClickListener implements View.OnClickListener{
    MainActivity parent_;
    String name_;

    public RecommendationElementClickListener(MainActivity m, String name) {
        parent_ = m;
        name_ = name;
    }

    public void onClick(View v) {
        if (parent_ != null) {
            parent_.openDialog(BottomSheetCodes.PreviewTemplate, name_);
        }
    }
}
