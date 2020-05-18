package com.thide11.ui.drone_control;

import com.thide11.utilities.TelloCommands;
import com.thide11.utilities.TelloConstants;

public class DroneRcData {
    int leftRight;
    int forward;
    int upDown;
    //Default is clockWise
    int rotation;
    int speed;

    public DroneRcData() {
        leftRight = 0;
        forward = 0;
        upDown = 0;
        rotation = 0;
        speed = TelloConstants.DEFAULT_TELLO_SPEED;
    }

    public String convertToDroneCommand(){
        int speed = this.getSpeed();
        return String.format(TelloCommands.RC_CONTROL, this.getLeftRight() * speed, this.getForward() * speed, this.getUpDown() * speed, this.getRotation() * speed);
    }

    public int getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getUpDown() {
        return upDown;
    }

    public void setUpDown(int upDown) {
        this.upDown = upDown;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int velocidade) {
        this.speed = velocidade;
    }
}
