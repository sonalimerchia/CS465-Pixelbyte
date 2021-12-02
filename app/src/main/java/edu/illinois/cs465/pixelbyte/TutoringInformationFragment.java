package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TutoringInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutoringInformationFragment extends DialogFragment {

    String department_;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TutoringInformationFragment() {
        // Required empty public constructor
    }

    public TutoringInformationFragment(String department) {
        department_ = department;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TutoringInformationFragment.
     */
    public static TutoringInformationFragment newInstance(String param1) {
        TutoringInformationFragment fragment = new TutoringInformationFragment();
        Bundle args = new Bundle();
        args.putString(fragment.department_, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(department_);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View dialog =  inflater.inflate(R.layout.fragment_tutoring_information, container, false);

        TextView label = dialog.findViewById(R.id.department_label);
        if (label != null) {
            label.setText(department_);
        }

        TextView website = dialog.findViewById(R.id.department_website);
        if (website != null) {
            website.setText(department_.toLowerCase() + ".illinois.edu/help");
        }

        TextView email = dialog.findViewById(R.id.department_email);
        if (email != null) {
            email.setText((department_.toLowerCase() + "tutoring@illinois.edu"));
        }

        ImageView close = dialog.findViewById(R.id.close_dialog);
        if (close != null) {
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDestroyView();
                }
            });
        }

        return dialog;
    }
}