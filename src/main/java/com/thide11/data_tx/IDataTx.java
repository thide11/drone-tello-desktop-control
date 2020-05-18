package com.thide11.data_tx;

import java.io.IOException;

public interface IDataTx {
    // @return if is connected suceffuly
    boolean startConnect();
    void disconnect();
    void startSendVideo();
    void stopSendVideo();
    void takeOfDrone();
    void landDrone();
    void moveForward(int amount);
    void moveBackward(int amount);
    void moveRight(int amount);
    void moveLeft(int amount);
    void moveUp(int amount);
    void moveDown(int amount);
    void rotateRight(int degrees);
    void rotateLeft(int degrees);
    void sendPersonalizedCommand(String comando) throws IOException;
    //void moveDown(int amount);
}
