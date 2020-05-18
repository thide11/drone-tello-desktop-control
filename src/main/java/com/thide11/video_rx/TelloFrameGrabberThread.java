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
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

public class TelloFrameGrabberThread extends Thread {
    private TelloVideoThread videoThread;

    TelloFrameGrabberThread(TelloVideoThread videoThread) {
        setName("Frame-Grabber");
        this.videoThread = videoThread;
    }

    public void run() {
        /*if (TelloSDKValues.DEBUG)*///avutil.av_log_set_level(avutil.AV_LOG_ERROR);
        //else avutil.av_log_set_level(avutil.AV_LOG_FATAL);
        Java2DFrameConverter conv = new Java2DFrameConverter();
        CustomFFmpegFrameGrabber fg = new CustomFFmpegFrameGrabber(videoThread.pis);
        fg.setImageMode(FrameGrabber.ImageMode.COLOR);
        fg.setFormat("h264");
        fg.setFrameRate(30);
        fg.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        fg.setImageWidth(TelloConstants.VIDEO_WIDTH);
        fg.setImageHeight(TelloConstants.VIDEO_HEIGHT);
        try {
            fg.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            return;
        }
        Frame f;
        while (videoThread.running) {
            try {
                f = fg.grabImage();
                if (f != null) {
                    Frame cloned = f.clone();
                    TelloVideoFrame frame = new TelloVideoFrame(conv.convert(cloned), cloned);;
                    videoThread.queue.queueFrame(frame);
                }
            } catch (FrameGrabber.Exception exception) {
                exception.printStackTrace();
            }
        }
        try {
            fg.release();
            fg.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}
