package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateClass extends AppCompatActivity {
    BottomSheetDialogFragment openDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Create Class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        Button newTemplateButton = (Button) findViewById(R.id.new_template_button);
        newTemplateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openDialog(BottomSheetCodes.NewTemplatePreview, "Edit Template");
            }
        });


        Button selectTemplateButton = (Button) findViewById(R.id.choose_template_button);
        Intent intentSelect = new Intent(this, SelectTemplates.class);
        selectTemplateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intentSelect);
            }
        });
    }

    private void openDialog(BottomSheetCodes code, String bottomSheetName) {
        switch (code) {
            case NewTemplatePreview:
                openDialog = new CreateNewTemplate();
                break;
            case AddCategory:
                openDialog = new AddCategory();
                break;
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }
}