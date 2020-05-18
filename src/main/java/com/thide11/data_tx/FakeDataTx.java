package com.thide11.data_tx;

import com.thide11.translation.Translation;

import java.io.IOException;

public class FakeDataTx implements IDataTx {
    @Override
    public boolean startConnect() {
        new Translation();
        return true;
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void startSendVideo() {

    }

    @Override
    public void stopSendVideo() {

    }

    @Override
    public void takeOfDrone() {

    }

    @Override
    public void landDrone() {

    }

    @Override
    public void moveForward(int amount) {

    }

    @Override
    public void moveBackward(int amount) {

    }

    @Override
    public void moveRight(int amount) {

    }

    @Override
    public void moveLeft(int amount) {

    }

    @Override
    public void moveUp(int amount) {

    }

    @Override
    public void moveDown(int amount) {

    }

    @Override
    public void rotateRight(int degrees) {

    }

    @Override
    public void rotateLeft(int degrees) {

    }

    @Override
    public void sendPersonalizedCommand(String comando) throws IOException {
        System.out.println(Translation.getStringStatic("receivedCommand") + comando);
    }
}
