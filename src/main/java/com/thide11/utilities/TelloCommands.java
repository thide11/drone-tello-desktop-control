package com.thide11.utilities;

public interface TelloCommands {
    //Control Commands

    String CONNECT = "command";
    String START_VIDEO_STREAM = "streamon";
    String STOP_VIDEO_STREAM = "streamoff";
    String LAND = "land";
    String TAKE_OFF = "takeoff";
    //Caution! : THIS IS STOP ALL MOTORS IMMEDIATELY
    //String EMERGENCY = "emergency";


    //Comands that need string format
    String UP = "up %d";
    String DOWN = "down %d";
    String LEFT = "left %d";
    String RIGHT = "right %d";
    String FORWARD ="forward %d";
    String BACK ="back %d";
    String ROTATE_RIGHT ="cw %d";
    String ROTATE_LEFT ="ccw %d";
    String RC_CONTROL ="rc %d %d %d %d";

    //Read Commands
    String SPEED = "speed?";
    String BATERRY = "battery?";
    String TIME = "time?";
    String HEIGHT = "height?";
    String TEMP = "temp?";
    String ATTITUDE = "attitude?";
    String BAROMETER = "baro?";
    //Get wifi signal
    String WIFI = "wifi?";
}
