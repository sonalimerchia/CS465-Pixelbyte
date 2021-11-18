package edu.illinois.cs465.pixelbyte.ClassStructures;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class Assignment {
    public String name_;
    public double earnedPoints_;
    public double maxPoints_;

    public Assignment() {
        name_ = "";
        earnedPoints_ = 0;
        maxPoints_ = 0;
    }

    public Assignment(String name, double earned, double max) {
        name_ = name;
        earnedPoints_ = earned;
        maxPoints_ = max;
    }

    private static String makeIdentifier(int categoryIndex, int assignmentIndex) {
        return "Assignment" + categoryIndex + "-" + assignmentIndex;
    }

    public static Assignment extract(Intent intent, int categoryIndex, int assignmentIndex) {
        Bundle extras = intent.getExtras();
        String assignmentIdentifier = makeIdentifier(categoryIndex, assignmentIndex);

        String name = extras.getString(assignmentIdentifier + "Name");
        double earned = extras.getDouble(assignmentIdentifier + "Earned");
        double max = extras.getDouble(assignmentIdentifier + "Max");

        return new Assignment(name, earned, max);
    }

    public void addToIntent(Intent intent, int categoryIndex, int assignmentIndex) {
        String assignmentIdentifier = makeIdentifier(categoryIndex, assignmentIndex);

        intent.putExtra(assignmentIdentifier + "Name", name_);
        intent.putExtra(assignmentIdentifier + "Earned", earnedPoints_);
        intent.putExtra(assignmentIdentifier + "Max", maxPoints_);
    }
}
