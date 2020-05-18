package com.thide11;

import com.thide11.data_tx.DataTx;
import com.thide11.data_tx.FakeDataTx;
import com.thide11.data_tx.IDataTx;
import com.thide11.translation.Translation;
import com.thide11.ui.JFrameUi;
import com.thide11.ui.Ui;
import com.thide11.utilities.TelloConstants;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final IDataTx dataTx = new DataTx();
        boolean connected = dataTx.startConnect();

        if(connected) {
            new JFrameUi(dataTx);

        /*

        new Thread(new Runnable() {
            public void run() {

         */
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println(Translation.getStringStatic("type_command"));
                String comando = scan.nextLine();
                try {
                    dataTx.sendPersonalizedCommand(comando);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //});
        }


        //dataTx

    }
}
