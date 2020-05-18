package com.thide11.data_rx;

import com.thide11.translation.Translation;
import org.junit.Test;

import static org.junit.Assert.*;


public class DataRxTest {
    @Test
    public void testTelloDataRead() {
        new Translation();

        String droneSendData = "pitch:2;roll:3;yaw:1;vgx:2;vgy:2;vgz:1;templ:30;temph:55;tof:10;h:0;bat:0;baro: 0.52;time:25;agx:0.7;agy:0.02;agz:0.06;\r\n";
        TelloData telloData = new TelloData(droneSendData);
        TelloData response = new TelloData(
                2, 3, 1, 2, 2, 1, 30, 55, 10,
                0, 0, 0.52F, 25, 0.7F, 0.02F, 0.06F);
        assertTrue("Data recebida é congruente", telloData.equals(response));
    }
}