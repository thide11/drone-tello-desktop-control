package com.thide11.ui.drone_control;

import com.thide11.data_tx.IDataTx;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class MotionObserver implements KeyListener, MouseMotionListener {

    IDataTx iDataTx;
    DroneRcData droneRcData;
    DroneControlUpdate droneControlUpdate;
    //Send data to drone
    public MotionObserver(IDataTx iDataTx, DroneControlUpdate droneControlUpdate) {
        this.droneControlUpdate = droneControlUpdate;
        this.iDataTx = iDataTx;
        this.droneRcData = new DroneRcData();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed : " + e.getKeyChar());
        // Controle de alimento
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                droneRcData.setForward(1);
                //iDataTx.moveForward(20);
                break;
            case KeyEvent.VK_S:
                droneRcData.setForward(-1);
                //iDataTx.moveBackward(20);
                break;
            case KeyEvent.VK_D:
                droneRcData.setLeftRight(1);
                //iDataTx.moveRight(20);
                break;
            case KeyEvent.VK_A:
                droneRcData.setLeftRight(-1);
                //iDataTx.moveLeft(20);
                break;
            case KeyEvent.VK_R:
                droneRcData.setUpDown(1);
                //iDataTx.moveUp(20);
                break;
            case KeyEvent.VK_F:
                droneRcData.setUpDown(-1);
                //iDataTx.moveDown(20);
                break;
            case KeyEvent.VK_L:
                iDataTx.landDrone();
                break;
            case KeyEvent.VK_O:
                iDataTx.takeOfDrone();
                break;
            case KeyEvent.VK_Q:
                droneRcData.setRotation(-1);
                //iDataTx.rotateLeft(10);
                break;
            case KeyEvent.VK_E:
                droneRcData.setRotation(1);
                //iDataTx.rotateRight(10);
                break;
            default:
                System.out.println("Uncaught key : " + e.getKeyChar());
        }
        sendRcCommandToDrone();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if(droneRcData.getForward() == 1) {
                    droneRcData.setForward(0);
                }
                //iDataTx.moveForward(20);
                break;
            case KeyEvent.VK_S:
                if(droneRcData.getForward() == -1) {
                    droneRcData.setForward(0);
                }
                //iDataTx.moveBackward(20);
                break;
            case KeyEvent.VK_D:
                if(droneRcData.getLeftRight() == 1) {
                    droneRcData.setLeftRight(0);
                }
                //iDataTx.moveRight(20);
                break;
            case KeyEvent.VK_A:
                if(droneRcData.getLeftRight() == -1) {
                    droneRcData.setLeftRight(0);
                }
                //iDataTx.moveLeft(20);
                break;
            case KeyEvent.VK_R:
                if(droneRcData.getUpDown() == 1) {
                    droneRcData.setUpDown(0);
                }
                //iDataTx.moveUp(20);
                break;
            case KeyEvent.VK_F:
                if(droneRcData.getUpDown() == -1) {
                    droneRcData.setUpDown(0);
                }
                //iDataTx.moveDown(20);
                break;
            case KeyEvent.VK_Q:
                if(droneRcData.getRotation() == -1) {
                    droneRcData.setRotation(0);
                }
                //iDataTx.rotateLeft(10);
                break;
            case KeyEvent.VK_E:
                if(droneRcData.getRotation() == 1) {
                    droneRcData.setRotation(0);
                }
                //iDataTx.rotateRight(10);
                break;
            default:
                System.out.println("Uncaught key : " + e.getKeyChar());
        }
        sendRcCommandToDrone();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(String.format("Mouse movido : %dx%d", e.getX(), e.getY()));
    }

    private void sendRcCommandToDrone() {
        try {
            droneControlUpdate.onDroneControlUpdate(droneRcData);
            iDataTx.sendPersonalizedCommand(droneRcData.convertToDroneCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
