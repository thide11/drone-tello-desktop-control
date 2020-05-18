package com.thide11.video_rx;

public interface VideoStreamListener {

    void onVideoServerStarted();

    void onVideoReceived(TelloVideoFrame frame);

    void onVideoServerStopped();
}