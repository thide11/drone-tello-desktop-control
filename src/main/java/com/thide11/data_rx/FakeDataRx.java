package com.thide11.data_rx;

public class FakeDataRx implements IDataRx, Runnable{

    DataStreamListener dataStreamListener;

    public FakeDataRx(DataStreamListener dataStreamListener) {
        this.dataStreamListener = dataStreamListener;
        new Thread(this).start();
    }

    @Override
    public void run() {
        int time = 0;
        while (true) {
            time++;
            try {
                Thread.sleep(time);
                TelloData data = new TelloData(
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0.52F, time, 0.7F, 0.02F, 0.06F);
                dataStreamListener.onDataReceived(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
