package com.thide11.data_rx;

import com.thide11.utilities.TelloConstants;
import com.thide11.utilities.Utilities;

import java.io.IOException;
import java.net.*;

public class DataRx implements IDataRx, Runnable {

    DatagramSocket serverSocket;
    DataStreamListener dataStreamListener;

    public DataRx(DataStreamListener dataStreamListener) {
        try {
            InetAddress inetAddress = InetAddress.getByAddress(
                    Utilities.convertTelloAdressToByte(TelloConstants.RX_SERVER_IP)
            );
            this.dataStreamListener = dataStreamListener;
            this.serverSocket = new DatagramSocket(TelloConstants.PORT_RX_STATE, inetAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                //System.out.println("Esperando por datagrama UDP na porta " + TelloConstants.PORT_RX_VIDEO);
                serverSocket.receive(receivePacket);
                //System.out.print("Datagrama UDP recebido!");
                String data = new String(receivePacket.getData());
                dataStreamListener.onDataReceived(new TelloData(data));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
