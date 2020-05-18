/*
 * Copyright 2020 Fritz Windisch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.thide11.video_rx;


import com.thide11.utilities.TelloConstants;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

public class TelloVideoThread extends Thread implements IVideoRx{
    boolean running = true;
    TelloVideoQueue queue;
    PipedInputStream pis = new PipedInputStream();
    private DatagramSocket ds;
    private List<byte[]> currentFrame = new LinkedList<>();
    private PipedOutputStream pos;

    private boolean streamAligned = false;
    private byte[] buf = new byte[2048];
    private TelloFrameGrabberThread frameGrabberThread;
    private VideoStreamListener videoStreamListener;

    public TelloVideoThread(VideoStreamListener videoStreamListener) {
        this.videoStreamListener = videoStreamListener;
        this.queue = new TelloVideoQueue(this);
        try {
            this.pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            System.out.println("Error on constructing video stream" + e);
        }
        frameGrabberThread = new TelloFrameGrabberThread(this);
    }

    @Override
    public void connect() {
        try {
            ds = new DatagramSocket(TelloConstants.PORT_RX_VIDEO, InetAddress.getByName(TelloConstants.RX_SERVER_IP));
            ds.setSoTimeout(TelloConstants.TIMEOUT_TIME);
            this.start();
        } catch (Exception e) {
            System.out.println("Error while creating stream receive socket : " + e);
        }
    }

    public void run() {
        videoStreamListener.onVideoServerStarted();
        queue.start();
        frameGrabberThread.start();
        setName("Stream-Thread");
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ds.receive(packet);
                handleInput(buf, packet.getLength());
            } catch (Exception e) {
                //Ignore missing updates - no way to error them
                //Disconnect at end of program is also intended to end here
            }
        }
        try {
            pos.close(); //This will shut down the frame grabber as well
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleInput(byte[] bytes, int length) {
        if (!streamAligned) {
            if (length != 1460) streamAligned = true;
            return;
        }
        try {
            pos.write(bytes, 0, length);
            pos.flush();
        } catch (IOException e) {
            System.out.println("Error while pushing data" + e);
        }
    }

    public void kill() {
        running = false;
        queue.kill();
        ds.close();
    }

    public VideoStreamListener getVideoStreamListener() {
        return videoStreamListener;
    }
}
