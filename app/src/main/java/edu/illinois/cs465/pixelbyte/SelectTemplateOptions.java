package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectTemplateOptions extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_class, container, false);

        /*Instantiate Views and attach Listeners*/

        /* TODO: Connect to premade templates/Simulate templates and populate the ListView with them */
        /* TODO: Button navigation and logic */

        return view;
    }
}