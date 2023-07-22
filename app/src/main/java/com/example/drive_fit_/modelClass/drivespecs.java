package com.example.drive_fit_.modelClass;

import java.util.ArrayList;

public class drivespecs {
    ArrayList<Float> x;
    ArrayList<Float> y;
    Float maxX;
    Float maxY;

    public drivespecs(ArrayList<Float> x, ArrayList<Float> y, Float maxX, Float maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public drivespecs() {
    }

    public ArrayList<Float> getX() {
        return x;
    }

    public void setX(ArrayList<Float> x) {
        this.x = x;
    }

    public ArrayList<Float> getY() {
        return y;
    }

    public void setY(ArrayList<Float> y) {
        this.y = y;
    }

    public Float getMaxX() {
        return maxX;
    }

    public void setMaxX(Float maxX) {
        this.maxX = maxX;
    }

    public Float getMaxY() {
        return maxY;
    }

    public void setMaxY(Float maxY) {
        this.maxY = maxY;
    }
}
