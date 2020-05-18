package com.thide11.data_rx;

import com.thide11.video_rx.TelloVideoFrame;

public interface DataStreamListener {


    void onDataReceived(TelloData telloData);
}