package edu.illinois.cs465.pixelbyte.CategoryList;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;
import edu.illinois.cs465.pixelbyte.ViewCategory;

public class ClassCategoryElementClickListener implements View.OnClickListener {
    TemplateCategory category_;

    public ClassCategoryElementClickListener(TemplateCategory tc) {
        category_ = tc;
    }

    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, ViewCategory.class);

        category_.addToIntent(intent, 0);

        context.startActivity(intent);
    }
}
