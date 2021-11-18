package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import edu.illinois.cs465.pixelbyte.categoryCreation.CategoryArrayAdapter;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class PreviewTemplate extends BottomSheetDialogFragment {
    String className_;

    public PreviewTemplate(String name) {
        className_ = name;
    }

    private void setContent (View view) {
        // Set title to class name
        TextView title = (TextView) view.findViewById(R.id.template_name_field);
        title.setText(className_);

        ListView template = (ListView) view.findViewById(R.id.template);
        List<TemplateCategory> items = TemplateCategory.createItems();

        // Create adapter to interpret data
        CategoryArrayAdapter arr = new CategoryArrayAdapter(view.getContext(), items, R.layout.template_list_item);

        // Apply adapter to list
        template.setAdapter(arr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_preview_template, container, false);

        setContent(view);

        // Enable Back Button
        ImageView back = (ImageView) view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity m = (MainActivity)(getActivity());
                m.openDialog(BottomSheetCodes.FindTemplate, "FindTemplate");
            }
        });

        // Enable Edit Button
        Button edit = (Button) view.findViewById(R.id.edit_template_button);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity m = (MainActivity)(getActivity());
                m.openDialog(BottomSheetCodes.NewTemplatePreview, "NewTemplate");
            }
        });

        // Enable Use Button
        Button use = (Button) view.findViewById(R.id.use_template_button);
        use.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity m = (MainActivity)(getActivity());
                // TODO: Implement use template
            }
        });



        return view;
    }
}