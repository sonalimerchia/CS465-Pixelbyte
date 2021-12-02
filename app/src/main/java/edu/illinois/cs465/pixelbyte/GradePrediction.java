package edu.illinois.cs465.pixelbyte;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GradePrediction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradePrediction extends DialogFragment {
    double prediction_;

    public GradePrediction() {
        // Required empty public constructor
    }

    public GradePrediction(double prediction) {
        prediction_ = prediction;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GradePrediction.
     */
    // TODO: Rename and change types and number of parameters
    public static GradePrediction newInstance(String param1, String param2) {
        GradePrediction fragment = new GradePrediction();
        Bundle args = new Bundle();
        args.putDouble("prediction", fragment.prediction_);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prediction_ = getArguments().getDouble("prediction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_grade_prediction, container, false);

        double roundedPrediction = (int)(prediction_ * 100) / 100.0;
        TextView predictionDisplay = convertView.findViewById(R.id.grade_prediction);
        predictionDisplay.setText(roundedPrediction + "%");

        return convertView;
    }
}