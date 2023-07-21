package com.example.drive_fit_.modelClass;

public class weight_train {

    int exercisepic;
    String exercisename;

    public weight_train(int exercisepic, String exercisename) {
        this.exercisepic = exercisepic;
        this.exercisename = exercisename;
    }

    public int getExercisepic() {
        return exercisepic;
    }

    public void setExercisepic(int exercisepic) {
        this.exercisepic = exercisepic;
    }

    public String getExercisename() {
        return exercisename;
    }

    public void setExercisename(String exercisename) {
        this.exercisename = exercisename;
    }
}
