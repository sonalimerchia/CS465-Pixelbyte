package edu.illinois.cs465.pixelbyte.categoryCreation;

public class TemplateCategory {
    String name;
    double weight;
    int drops;
    int assignments;

    public TemplateCategory(String n, double w, int d, int a) {
        name = n;
        weight = w;
        drops = d;
        assignments = a;
    }

    public static TemplateCategory[] createItems() {
        String[] names = {"Quizzes", "Homework", "Assignments"};
        double[] weights = {20.0, 15.0, 5.5};
        int[] drops = {2, 3, 0};
        int[] assignments = {15, 20, 30};

        TemplateCategory[] c = new TemplateCategory[names.length];
        for (int index = 0; index < names.length; ++index) {
            c[index] = new TemplateCategory(names[index], weights[index], drops[index], assignments[index]);
        }

        return c;
    }
}
