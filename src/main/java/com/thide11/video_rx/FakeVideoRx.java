package com.thide11.video_rx;

import com.thide11.utilities.TelloConstants;
import com.thide11.utilities.Utilities;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FakeVideoRx implements IVideoRx {

    private VideoStreamListener videoStreamListener;
    private DatagramSocket serverSocket;

    public FakeVideoRx(VideoStreamListener videoStreamListener) throws SocketException, UnknownHostException {
        try {
            this.videoStreamListener = videoStreamListener;
            InetAddress inetAddress = InetAddress.getByAddress(
                    Utilities.convertTelloAdressToByte(TelloConstants.RX_SERVER_IP)
            );
            //this.serverSocket = new DatagramSocket(TelloConstants.PORT_RX_VIDEO, inetAddress);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on test");
        }
    }

    @Override
    public void connect() {
        videoStreamListener.onVideoServerStarted();
    }
}
