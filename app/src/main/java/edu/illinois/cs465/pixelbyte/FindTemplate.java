package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.ClassList.ClassData;
import edu.illinois.cs465.pixelbyte.RecommendedTemplateList.RecommendedTemplateAdapter;
import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class FindTemplate extends BottomSheetDialogFragment {
    ListView recommendedTemplates_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_find_template, container, false);

        // Create items from sample data
        List<ClassData> items = ClassData.createSampleList();

        // Create adapter to interpret data
        recommendedTemplates_ = view.findViewById(R.id.recommended_templates);
        RecommendedTemplateAdapter arr = new RecommendedTemplateAdapter(view.getContext(), items, (MainActivity)(getActivity()));

        // Apply adapter to list
        recommendedTemplates_.setAdapter(arr);

        // Enable back button
        ImageView back = (ImageView) view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity m = (MainActivity)getActivity();
                m.openDialog(BottomSheetCodes.CreateClass, "CreateClass");
            }
        });

        Button createNew = (Button) view.findViewById((R.id.create_new_template));
        createNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity m = (MainActivity) getActivity();
                m.openDialog(BottomSheetCodes.NewTemplatePreview, "New Template");
            }
        });

        return view;
    }
}