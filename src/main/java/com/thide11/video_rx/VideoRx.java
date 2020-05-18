package com.thide11.video_rx;


import com.thide11.utilities.TelloConstants;
import com.thide11.utilities.Utilities;
import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;

import java.io.IOException;
import java.net.*;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264;
import static org.bytedeco.ffmpeg.global.avcodec.avcodec_find_decoder;
import static org.bytedeco.ffmpeg.global.avformat.avformat_new_stream;

public abstract class VideoRx implements IVideoRx, Runnable {

    private VideoStreamListener videoStreamListener;
    private DatagramSocket serverSocket;

    public VideoRx(VideoStreamListener videoStreamListener) throws SocketException, UnknownHostException {
        this.videoStreamListener = videoStreamListener;
        InetAddress inetAddress = InetAddress.getByAddress(
                Utilities.convertTelloAdressToByte(TelloConstants.RX_SERVER_IP)
        );
        this.serverSocket = new DatagramSocket(TelloConstants.PORT_RX_VIDEO, inetAddress);
        new Thread(this).start();
    }

    public void run() {
        try {
            Thread.sleep(2000);
            videoStreamListener.onVideoServerStarted();
            byte[] receiveData = new byte[1024];
            while (true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                System.out.println("Esperando por datagrama UDP na porta " + TelloConstants.PORT_RX_VIDEO);
                serverSocket.receive(receivePacket);
                System.out.print("Datagrama UDP recebido!");

                byte[] imagemStream = receivePacket.getData();
                /*
                SeekableByteChannel a = new SeekableByteChannel() {
                }
                FrameGrab.getNativeFrame()

                System.out.println(sentence);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                String capitalizedSentence = sentence.toUpperCase();

                sendData = capitalizedSentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, port);

                System.out.print("Enviando " + capitalizedSentence + "...");

                serverSocket.send(sendPacket);
                
                 */
                System.out.println("OK\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
