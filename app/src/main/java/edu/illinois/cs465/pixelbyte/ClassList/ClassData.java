package edu.illinois.cs465.pixelbyte.ClassList;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.pixelbyte.categoryCreation.TemplateCategory;

public class ClassData {
    public String className_;
    public String letterGrade_;
    public double numberGrade_;
    public double goal_;
    public int color_;
    public List<TemplateCategory> categories_;

    public ClassData(String classname, String let, double num) {
        className_ = classname;
        letterGrade_ = let;
        numberGrade_ = num;
        color_ = 0;
        goal_ = 0;
        categories_ = new ArrayList<>();
    }

    public ClassData(String classname, String let, double num, int col) {
        className_ = classname;
        letterGrade_ = let;
        numberGrade_ = num;
        color_ = col;
        goal_ = 0;
        categories_ = new ArrayList<>();
    }

    public ClassData(String classname, String let, double num, int col, double gol) {
        className_ = classname;
        letterGrade_ = let;
        numberGrade_ = num;
        color_ = col;
        goal_ = gol;
        categories_ = new ArrayList<>();
    }

    public String makeGradeString() {
        return (Math.round(numberGrade_ * 100) / 100.0) + "%";
    }

    public String makeGoalString() {
        return (Math.round(goal_ * 100) / 100.0) + "%";
    }

    public static ClassData extract(Intent intent) {
        String cn = intent.getExtras().getString("ClassName");
        String lg = intent.getExtras().getString("LetterGrade");
        double ng = intent.getExtras().getDouble("NumberGrade");
        double gol = intent.getExtras().getDouble("Goal");
        int col = intent.getExtras().getInt("Color");

        return new ClassData(cn, lg, ng, col, gol);
    }

    public void addToIntent(Intent intent) {
        intent.putExtra("ClassName", className_);
        intent.putExtra("LetterGrade", letterGrade_);
        intent.putExtra("NumberGrade", numberGrade_);
        intent.putExtra("Goal", goal_);
        intent.putExtra("Color", color_);
    }

    public static List<ClassData> createSampleList() {
        List<ClassData> classes = new ArrayList<>();

        classes.add(new ClassData("CS 125", "A", 95.5, 0xffffff00, 90.0));
        classes.add(new ClassData("CS 465", "C+", 78.9, 0xff00ff00, 90.0));
        classes.add(new ClassData("CS 233", "B-", 83.9, 0xff00ffff, 80.0));

        addCategories(classes.get(0));
        addCategories(classes.get(1));
        addCategories(classes.get(2));

        return classes;
    }

    private static void addCategories(ClassData cd) {
        cd.categories_.add(new TemplateCategory("Quizzes", 20.5, 2, 13));
        cd.categories_.add(new TemplateCategory("Homework", 15.0, 5, 25));
        cd.categories_.add(new TemplateCategory("Exams", 33.3, 0, 3));
    }
}
