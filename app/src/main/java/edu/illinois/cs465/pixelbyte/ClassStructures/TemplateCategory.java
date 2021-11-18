package edu.illinois.cs465.pixelbyte.ClassStructures;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TemplateCategory {
    public String name_;
    public double weight_;
    public int drops_;
    public int assignments_;
    public List<Assignment> enteredAssignments_;

    public TemplateCategory(String n, double w, int d, int a) {
        name_ = n;
        weight_ = w;
        drops_ = d;
        assignments_ = a;
        enteredAssignments_ = new ArrayList<>();
    }

    public static List<TemplateCategory> createItems() {
        String[] names = {"Quizzes", "Homework", "Assignments"};
        double[] weights = {20.0, 15.0, 5.5};
        int[] drops = {2, 3, 0};
        int[] assignments = {15, 20, 30};

        List<TemplateCategory> c = new ArrayList<>();
        for (int index = 0; index < names.length; ++index) {
            c.add(new TemplateCategory(names[index], weights[index], drops[index], assignments[index]));

            for (int assignmentCount = 0; assignmentCount < 5; ++assignmentCount) {
                double max = (int)(Math.random()*99 + 1);
                double earned = max * (Math.random()*0.2+0.8);
                earned = (int)(earned * 100)/100.0;
                c.get(index).enteredAssignments_.add(new Assignment(names[index] + " " + (assignmentCount + 1), earned, max));
            }
        }

        return c;
    }

    public static TemplateCategory extract(Bundle extras, int idx) {

        String name = extras.getString("CategoryName" + (idx + 1));
        double weight = extras.getDouble("CategoryWeight" + (idx + 1));
        int drops = extras.getInt("CategoryDrops" + (idx + 1));
        int numAssignments = extras.getInt("CategoryAssignments" + (idx + 1));

        TemplateCategory tc = new TemplateCategory(name, weight, drops, numAssignments);
        int numEntered = extras.getInt("CategoryEnteredAssignments" + (idx + 1));

        for (int assignIdx = 0; assignIdx < numEntered; ++assignIdx) {
            tc.enteredAssignments_.add(Assignment.extract(extras, idx, assignIdx));
        }

        return tc;
    }

    public void addToIntent(Intent intent, int idx) {
        intent.putExtra("CategoryName" + (idx + 1), name_);
        intent.putExtra("CategoryWeight" + (idx + 1), weight_);
        intent.putExtra("CategoryDrops" + (idx + 1), drops_);
        intent.putExtra("CategoryAssignments" + (idx + 1), assignments_);
        intent.putExtra("CategoryEnteredAssignments" + (idx + 1), enteredAssignments_.size());

        for (int assignIdx = 0; assignIdx < enteredAssignments_.size(); ++assignIdx) {
            enteredAssignments_.get(assignIdx).addToIntent(intent, idx, assignIdx);
        }
    }

    public String getGrade() {
        double totalEarned = 0;
        double totalPossible = 0;

        for (Assignment assignment : enteredAssignments_) {
            totalEarned += assignment.earnedPoints_;
            totalPossible += assignment.maxPoints_;
        }

        double result = Math.round(totalEarned / totalPossible * 10000)/100.0;

        return result + "%";
    }
}
