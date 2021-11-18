package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectTemplates extends BottomSheetDialogFragment {
    BottomSheetDialogFragment openDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set bottom sheet contents
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_select_templates, container, false);

        /*Instantiate Buttons and attach Listeners*/
        Button newTemplateButton = (Button) view.findViewById(R.id.new_template_button);
        newTemplateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openDialog(BottomSheetCodes.NewTemplatePreview, "Edit Template");
            }
        });


        Button selectTemplateButton = (Button) view.findViewById(R.id.choose_template_button);
        Intent intentSelect = new Intent(this, SelectTemplates.class);
        selectTemplateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intentSelect);
            }
        });

        /* TODO: Connect to premade templates/Simulate templates and populate the ListView with them */
        /* TODO: Button navigation and logic */

        return view;
    }

    private void openDialog(String bottomSheetName) {
        openDialog = new AddCategory();
        openDialog.show(getActivity().getSupportFragmentManager(), bottomSheetName);
    }
}