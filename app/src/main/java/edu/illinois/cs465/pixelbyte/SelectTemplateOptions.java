package edu.illinois.cs465.pixelbyte;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectTemplateOptions extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_create_new_class, container, false);

        /*Instantiate Views and attach Listeners*/

        /* TODO: Connect to premade templates and populate the ListView with them */
        /* TODO: Button navigation and logic */

        return view;
    }



}