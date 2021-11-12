package edu.illinois.cs465.pixelbyte.ClassList;

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
}
