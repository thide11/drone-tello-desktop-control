package com.thide11.utilities;

public class Utilities {

    public static byte[] convertTelloAdressToByte(String telloAdress) {
        String[] ips = telloAdress.split("\\.");
        byte[] ipResult = new byte[4];
        int x = 0;
        for(String ip : ips) {
            ipResult[x] = (byte) Integer.parseInt(ip);
            x++;
        }
        return ipResult;
    }

    public static byte[] convertTelloAdressToByte() {
        return convertTelloAdressToByte(TelloConstants.TELLO_IP);
    }
}
