package edu.illinois.cs465.pixelbyte.ClassStructures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import edu.illinois.cs465.pixelbyte.MainActivity;
import edu.illinois.cs465.pixelbyte.R;

public class ClassData {
    public String className_;
    public String letterGrade_;
    public String department_;
    public double numberGrade_;
    public double goal_;
    public int color_;

    private List<TemplateCategory> categories_;

    public ClassData(String classname, int color, String department) {
        className_ = classname;
        color_ = color;
        department_ = department;
        letterGrade_ = "";
        numberGrade_ = 0;
        goal_ = 0;
        categories_ = new ArrayList<>();
    }

    public ClassData(String classname, String let, int col, double gol, String department) {
        className_ = classname;
        letterGrade_ = let;
        color_ = col;
        goal_ = gol;
        department_ = department;
        numberGrade_ = 0;
        categories_ = new ArrayList<>();
    }

    public void addCategory(TemplateCategory newCategory) {
        TemplateCategory copy = new TemplateCategory(newCategory.name_, newCategory.weight_, newCategory.drops_, newCategory.assignments_);
        categories_.add(copy);
    }

    public List<TemplateCategory> getCategories() {
        return categories_;
    }

    public void addAssignment(String category, Assignment newAssignment) {
        for (TemplateCategory tc : categories_) {
            if (tc.name_.equals(category)) {
                tc.addAssignment(newAssignment);
                break;
            }
        }

        calculateGrade();
    }

    public String makeGradeString() {
        return (Math.round(numberGrade_ * 100) / 100.0) + "%";
    }

    public String makeGoalString() {
        return (Math.round(goal_ * 100) / 100.0) + "%";
    }

    public static ClassData extract(Bundle extras) {
        if (extras == null) return null;

        String cn = extras.getString("ClassName");
        String lg = extras.getString("LetterGrade");
        double ng = extras.getDouble("NumberGrade");
        double gol = extras.getDouble("Goal");
        int col = extras.getInt("Color");
        String dep = extras.getString("Department");

        ClassData cd = new ClassData(cn, lg, col, gol, dep);

        int numCategories = extras.getInt("NumCategories");
        for (int idx = 0; idx < numCategories; ++idx) {
            cd.categories_.add(TemplateCategory.extract(extras, idx));
        }

        cd.calculateGrade();

        return cd;
    }

    public void addToIntent(Intent intent) {
        intent.putExtra("ClassName", className_);
        intent.putExtra("LetterGrade", letterGrade_);
        intent.putExtra("NumberGrade", numberGrade_);
        intent.putExtra("Goal", goal_);
        intent.putExtra("Color", color_);
        intent.putExtra("Department", department_);

        intent.putExtra("NumCategories", categories_.size());
        for (int idx = 0; idx < categories_.size(); ++idx) {
            categories_.get(idx).addToIntent(intent, idx);
        }
    }

    public static List<ClassData> createSampleList(MainActivity parent) {
        List<ClassData> classes = new ArrayList<>();

        classes.add(new ClassData("CS 125", "A", 0xFF6FAFC7, 90.0, "CS"));
        classes.add(new ClassData("CS 465", "C+", 0xFFFFD125, 90.0, "CS"));
        classes.add(new ClassData("CS 233", "B-", 0xFFBFD46D, 80.0, "CS"));

        addCategories(classes.get(0));
        addCategories(classes.get(1));
        addCategories(classes.get(2));

        for (ClassData cd : classes) {
            cd.saveData(parent);
        }

        return classes;
    }

    private void calculateGrade() {
        numberGrade_ = 0;
        for (TemplateCategory tc : categories_) {
            numberGrade_ += tc.grade_ * tc.weight_ / 100;
        }

        int group = (int)(numberGrade_ / 10);
        if (group >= 9) letterGrade_ = "A";
        else if (group == 8) letterGrade_ = "B";
        else if (group == 7) letterGrade_ = "C";
        else if (group == 6) letterGrade_ = "D";
        else letterGrade_ = "F";

        int signIndicator = (int)(numberGrade_) % 10;
        if (signIndicator >= 7) letterGrade_ = letterGrade_ + "+";
        else if (signIndicator <=2 ) letterGrade_ = letterGrade_ + "-";
    }

    private static void addCategories(ClassData cd) {
        cd.categories_ = TemplateCategory.createItems();
        cd.calculateGrade();
    }

    public void saveData(Activity activity) {
        String filename = "class-"+className_+".json";
        try {
            FileOutputStream outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);

            Gson gson = new Gson();
            String classDataStr = gson.toJson(this);

            outputStream.write(classDataStr.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
