package com.thide11.ui;

import com.thide11.data_rx.*;
import com.thide11.data_tx.DataTx;
import com.thide11.data_tx.IDataTx;
import com.thide11.translation.Translation;
import com.thide11.ui.drone_control.DroneControlUpdate;
import com.thide11.ui.drone_control.DroneRcData;
import com.thide11.ui.drone_control.DroneStatePainter;
import com.thide11.ui.drone_control.MotionObserver;
import com.thide11.video_rx.*;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.swing.*;
import java.awt.*;

public class Ui extends JPanel implements VideoStreamListener, DataStreamListener, DroneControlUpdate {

    TelloData telloData;
    Translation translation;
    DroneRcData droneRcData;
    IDataTx dataTx;
    IDataRx dataRx;
    IVideoRx videoRx;
    private TelloVideoFrame frame;
    private Java2DFrameConverter converter = null;
    Dimension size;

    //Jlabels of state
    JLabel agxLabel = new JLabel();
    JLabel agyLabel = new JLabel();
    JLabel agzLabel = new JLabel();
    JLabel pitchLabel = new JLabel();
    JLabel rollLabel = new JLabel();
    JLabel yawLabel = new JLabel();
    JLabel bateryLabel = new JLabel();
    JLabel heightLabel = new JLabel();
    JLabel tofLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel baroLabel = new JLabel();
    JLabel vgxLabel = new JLabel();
    JLabel vgyLabel = new JLabel();
    JLabel vgzLabel = new JLabel();

    DroneStatePainter droneStatePainter;

    private void carregarUi(Dimension size, IDataTx dataTx, IDataRx dataRx, IVideoRx videoRx) {
        this.size = size;
        this.dataTx = dataTx;
        this.dataRx = dataRx;
        this.videoRx = videoRx;
        this.droneStatePainter = new DroneStatePainter();
        videoRx.connect();
        //Automatic start video
        dataTx.startSendVideo();
    }

    Ui(Dimension size, IDataTx dataTx, IDataRx dataRx, IVideoRx videoRx) {
        carregarUi(size, dataTx, dataRx, videoRx);
    }

    Ui(Dimension size) {
        this.size = size;
        dataTx = new DataTx();
        dataTx.startConnect();
        IDataRx dataRx = new DataRx(this);
        videoRx = new TelloVideoThread(this);
        carregarUi(size, dataTx, dataRx, videoRx);

        /*
        this.dataTx = dataTx;
        this.dataRx = dataRx;
        this.videoRx = videoRx;
         */
    }

    Ui(Dimension size, IDataTx dataTx) {
        IDataRx dataRx = new DataRx(this);
        IVideoRx videoRx = new TelloVideoThread(this);
        carregarUi(size, dataTx, dataRx, videoRx);
    }

    MotionObserver getMotionObserver() {
        return new MotionObserver(dataTx, this);
    }

    private void initInterface() {
        this.setPreferredSize(size);
        //this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(agxLabel);
        this.add(agyLabel);
        this.add(agzLabel);
        this.add(baroLabel);
        this.add(bateryLabel);
        this.add(heightLabel);
        this.add(pitchLabel);
        this.add(rollLabel);
        this.add(yawLabel);
        this.add(timeLabel);
        this.add(tofLabel);
        this.add(vgxLabel);
        this.add(vgyLabel);
        this.add(vgzLabel);

        this.setBackground(Color.gray);
        this.setVisible(true);
    }


    public void onVideoServerStarted() {
        System.out.println("Video started");
        initInterface();
    }

    public void onVideoReceived(TelloVideoFrame frame) {
        this.frame = frame;
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            if (frame != null) {
                if (frame.getExportType() == TelloVideoExportType.JAVACV_FRAME) {
                    if (converter == null) converter = new Java2DFrameConverter();
                    this.frame = new TelloVideoFrame(converter.convert(frame.getJavaCVFrame()), frame.getJavaCVFrame());
                }
                g2d.drawImage(frame.getImage(), 0, 0, null);
            }
        }
        droneStatePainter.refreshDronePainter(g, telloData, droneRcData);
    }

    public void onVideoServerStopped() {
        this.setVisible(false);
    }

    @Override
    public void onDroneControlUpdate(DroneRcData droneRcData) {
        this.droneRcData = droneRcData;
        repaint();
    }

    @Override
    public void onDataReceived(TelloData telloData) {
        this.telloData = telloData;
        if(translation == null)
            this.translation = new Translation();

        agxLabel.setText(String.format(translation.getString("agx"), telloData.getAgx()));
        agyLabel.setText(String.format(translation.getString("agy"), telloData.getAgy()));
        agzLabel.setText(String.format(translation.getString("agz"), telloData.getAgz()));
        baroLabel.setText(String.format(translation.getString("baro"), telloData.getBaro()));
        bateryLabel.setText(String.format(translation.getString("battery"), telloData.getBattery()));
        heightLabel.setText(String.format(translation.getString("height"), telloData.getHeight()));
        pitchLabel.setText(String.format(translation.getString("pitch"), telloData.getPitch()));
        rollLabel.setText(String.format(translation.getString("roll"), telloData.getRoll()));
        yawLabel.setText(String.format(translation.getString("yaw"), telloData.getYaw()));
        timeLabel.setText(String.format(translation.getString("time"), telloData.getTime()));
        tofLabel.setText(String.format(translation.getString("tof"), telloData.getTof()));
        vgxLabel.setText(String.format(translation.getString("vgx"), telloData.getVgx()));
        vgyLabel.setText(String.format(translation.getString("vgy"), telloData.getVgy()));
        vgzLabel.setText(String.format(translation.getString("vgz"), telloData.getVgz()));

        repaint();
    }
}


