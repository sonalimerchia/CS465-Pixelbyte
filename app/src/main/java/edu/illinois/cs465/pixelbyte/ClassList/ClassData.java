package edu.illinois.cs465.pixelbyte.ClassList;

public class ClassData {
    String className;
    String letterGrade;
    double numberGrade;
    double goal;
    int color;

    public ClassData(String classname, String let, double num) {
        className = classname;
        letterGrade = let;
        numberGrade = num;
        color = 0;
        goal = 0;
    }

    public ClassData(String classname, String let, double num, int col) {
        className = classname;
        letterGrade = let;
        numberGrade = num;
        color = col;
        goal = 0;
    }

    public ClassData(String classname, String let, double num, int col, double gol) {
        className = classname;
        letterGrade = let;
        numberGrade = num;
        color = col;
        goal = gol;
    }
}
