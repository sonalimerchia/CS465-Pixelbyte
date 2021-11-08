package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
    Button openDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialogButton = (Button) findViewById(R.id.trigger_open);
        openDialogButton.setOnClickListener(this);
    }

    private void openDialog(BottomSheetCodes code, String bottomSheetName) {
        switch (code) {
            case NewTemplatePreview:
                openDialog = new CreateNewTemplate();
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.trigger_open) {
            openDialog(BottomSheetCodes.NewTemplatePreview, "New Template");
        }
    }
}