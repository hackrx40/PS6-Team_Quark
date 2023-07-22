package com.example.drive_fit_.modelClass;

public class AcceleratometerModel {
    float accX;
    float accY;
    float accZ;
    float totalAcc;
    float totalDcc;

    float gyroX;
    float gyroY;
    float gyroZ;

    float accFactor;
    float dccFactor;
    float velFactor;
    float corFactor;

    float totalVel;
    float totalCorner;

    float driverScore;

    float timeStamp;


    public float getDriverScore() {
        return driverScore;
    }

    public void setDriverScore(float driverScore) {
        this.driverScore = driverScore;
    }

    public float getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(float timeStamp) {
        this.timeStamp = timeStamp;
    }

    public AcceleratometerModel(float timeStamp, float driverScore, float totalAcc, float totalDcc, float accFactor, float dccFactor, float velFactor, float corFactor, float totalVel, float totalCorner) {

        this.totalAcc = totalAcc;
        this.totalDcc = totalDcc;

        this.driverScore = driverScore;

        this.accFactor = accFactor;
        this.dccFactor = dccFactor;
        this.velFactor = velFactor;
        this.corFactor = corFactor;
        this.totalVel = totalVel;
        this.totalCorner = totalCorner;

        this.timeStamp = timeStamp;

    }

    public float getAccFactor() {
        return accFactor;
    }

    public void setAccFactor(float accFactor) {
        this.accFactor = accFactor;
    }

    public float getDccFactor() {
        return dccFactor;
    }

    public void setDccFactor(float dccFactor) {
        this.dccFactor = dccFactor;
    }

    public float getVelFactor() {
        return velFactor;
    }

    public void setVelFactor(float velFactor) {
        this.velFactor = velFactor;
    }

    public float getCorFactor() {
        return corFactor;
    }

    public void setCorFactor(float corFactor) {
        this.corFactor = corFactor;
    }

    public float getTotalVel() {
        return totalVel;
    }

    public void setTotalVel(float totalVel) {
        this.totalVel = totalVel;
    }

    public float getTotalCorner() {
        return totalCorner;
    }

    public void setTotalCorner(float totalCorner) {
        this.totalCorner = totalCorner;
    }

    public AcceleratometerModel(float accX, float accY, float accZ, float gyroX, float gyroY, float gyroZ) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;

        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
    }

    public float getGyroX() {
        return gyroX;
    }

    public void setGyroX(float gyroX) {
        this.gyroX = gyroX;
    }

    public float getGyroY() {
        return gyroY;
    }

    public void setGyroY(float gyroY) {
        this.gyroY = gyroY;
    }

    public float getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(float gyroZ) {
        this.gyroZ = gyroZ;
    }

    public AcceleratometerModel(float accX, float accY, float accZ, float totalAcc, float totalDcc) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
        this.totalAcc = totalAcc;
        this.totalDcc = totalDcc;
    }

    public AcceleratometerModel()
    {

    }

    public AcceleratometerModel(float accX, float accY, float accZ) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }

    public float getAccX() {
        return accX;
    }

    public void setAccX(float accX) {
        this.accX = accX;
    }

    public float getAccY() {
        return accY;
    }

    public void setAccY(float accY) {
        this.accY = accY;
    }

    public float getAccZ() {
        return accZ;
    }

    public void setAccZ(float accZ) {
        this.accZ = accZ;
    }

    public float getTotalAcc() {
        return totalAcc;
    }

    public void setTotalAcc(float totalAcc) {
        this.totalAcc = totalAcc;
    }

    public float getTotalDcc() {
        return totalDcc;
    }

    public void setTotalDcc(float totalDcc) {
        this.totalDcc = totalDcc;
    }
}
