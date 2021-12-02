package edu.illinois.cs465.pixelbyte.ClassStructures;

public class PredictorCategory {
    String name_;
    double earnedPoints_;
    double maxEarnedPoints_;

    double projectedPercentage_;
    double remainingPoints_;

    double currentGrade_;
    double projection_;

    public PredictorCategory(TemplateCategory tc) {
        name_ = tc.name_;
        earnedPoints_ = 0;
        maxEarnedPoints_ = 0;

        projectedPercentage_ = 0;
        remainingPoints_ = 0;

        for (Assignment a : tc.getAssignments()) {
            earnedPoints_ += a.earnedPoints_;
            maxEarnedPoints_ += a.maxPoints_;
        }

        currentGrade_ = earnedPoints_ * 100 / maxEarnedPoints_;
        projection_ = currentGrade_;
    }

    public String getName() {
        return name_;
    }

    public void setRemainingPoints(double remainingPoints) {
        remainingPoints_ = remainingPoints;
        calculateProjection();
    }

    public void setProjectedPercentage(double projectedPercentage) {
        projectedPercentage_ = projectedPercentage;
        calculateProjection();
    }

    public String getProjection() {
        double rounded = (int)(projection_ * 100) / 100.0;
        return rounded + "%";
    }

    public String getGrade() {
        double rounded = (int)(currentGrade_ * 100) / 100.0;
        return rounded + "%";
    }

    private void calculateProjection() {
        double futurePoints = (projectedPercentage_ / 100) * remainingPoints_;
        projection_ = (futurePoints + earnedPoints_) / (maxEarnedPoints_ + remainingPoints_);
        projection_ *= 100;
    }
}
