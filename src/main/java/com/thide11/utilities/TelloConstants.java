package com.thide11.utilities;

public interface TelloConstants {
    String TELLO_IP = "192.168.10.1";
    String RX_SERVER_IP = "0.0.0.0";
    int TIMEOUT_TIME = 2500;


    //Trasmission of data
    int PORT_TX = 8889;

    int PORT_RX_STATE = 8890;

    int PORT_RX_VIDEO = 11111;

    int VIDEO_WIDTH = 960;
    int VIDEO_HEIGHT = 720;

    //You can change this number to a between 10-100
    int DEFAULT_TELLO_SPEED = 30;

}
