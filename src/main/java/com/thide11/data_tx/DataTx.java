package com.thide11.data_tx;

import com.thide11.translation.Translation;
import com.thide11.utilities.TelloCommands;
import com.thide11.utilities.TelloConstants;
import com.thide11.utilities.Utilities;

import java.io.IOException;
import java.net.*;

public class DataTx implements IDataTx{

    private boolean isConnected = false;
    DatagramSocket clientSocket;
    InetAddress IPAddress;

    private String sendCommand(String command) {
        System.out.println("Received command to send : " + command);
        if(isConnected || command.equals(TelloCommands.CONNECT)) {
            try {
                //Send command
                byte[] sendData = command.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, TelloConstants.PORT_TX);
                clientSocket.send(sendPacket);

                //You can uncomment the receive response - this can cause delay on control

                //Receive response
                //byte[] receiveData = new byte[1024];
                //DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        //receiveData.length);
                //clientSocket.receive(receivePacket);
                //String modifiedSentence = new String(receivePacket.getData());
                //System.out.println("Drone answer with :" + modifiedSentence);
                return "ok";//modifiedSentence;
                //} while (clientSocket.getReceiveBufferSize() != 0);
            } catch (SocketTimeoutException e) {
                System.out.println(Translation.getStringStatic("dontResponse"));
                return null;
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
                return null;
            }
        } else {
            System.out.println(Translation.getStringStatic("dontConnected"));
            return null;
        }
    }

    public boolean startConnect() {
        try {
            if(clientSocket != null || isConnected) {
                System.out.println("Already connected");
            }
            clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(TelloConstants.TIMEOUT_TIME);
            IPAddress = InetAddress.getByAddress(Utilities.convertTelloAdressToByte());
            String response = sendCommand(TelloCommands.CONNECT);
            //System.out.println(respo);
            if(response.equals("ok")) {
                System.out.println("Connected!!");
                isConnected = true;
                return true;
            }

        } catch (UnknownHostException e) {
            isConnected = false;
            e.printStackTrace();
        } catch (SocketException e) {
            isConnected = false;
            e.printStackTrace();
        } catch (IOException e) {
            isConnected = false;
            e.printStackTrace();
        } finally {
            return isConnected;
        }
    }

    public void disconnect() {
        clientSocket.close();
    }

    public void startSendVideo() {
        sendCommand(TelloCommands.START_VIDEO_STREAM);
    }

    public void stopSendVideo() {
        sendCommand(TelloCommands.STOP_VIDEO_STREAM);
    }

    public void takeOfDrone() {
        sendCommand(TelloCommands.TAKE_OFF);
    }

    public void landDrone() {
        sendCommand(TelloCommands.LAND);
    }

    public void moveForward(int amount) {
        sendCommand(String.format(TelloCommands.FORWARD, amount));
    }

    public void moveBackward(int amount) {
        sendCommand(String.format(TelloCommands.BACK, amount));
    }

    public void moveRight(int amount) {
        sendCommand(String.format(TelloCommands.RIGHT, amount));
    }

    public void moveLeft(int amount) {
        sendCommand(String.format(TelloCommands.LEFT, amount));
    }

    public void moveUp(int amount) {
        sendCommand(String.format(TelloCommands.UP, amount));
    }

    public void moveDown(int amount) {
        sendCommand(String.format(TelloCommands.DOWN, amount));
    }

    public void rotateRight(int degrees) {
        sendCommand(String.format(TelloCommands.ROTATE_RIGHT, degrees));
    }

    public void rotateLeft(int degrees) {
        sendCommand(String.format(TelloCommands.ROTATE_LEFT, degrees));
    }

    public void sendPersonalizedCommand(String command) throws IOException {
        sendCommand(command);
    }
}
