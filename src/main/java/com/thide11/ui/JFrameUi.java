package com.thide11.ui;

import com.thide11.data_tx.IDataTx;
import com.thide11.utilities.TelloConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class JFrameUi extends JFrame{

    public JFrameUi(IDataTx data) {
        Dimension size = new Dimension(TelloConstants.VIDEO_WIDTH, TelloConstants.VIDEO_HEIGHT);
        this.setPreferredSize(size);
        Ui ui = new Ui(size, data);
        this.addMouseMotionListener(ui.getMotionObserver());
        this.addKeyListener(ui.getMotionObserver());
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setBackground(Color.WHITE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),
                BoxLayout.Y_AXIS));
        this.add(ui);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        this.getContentPane().setCursor(blankCursor);
        this.pack();
        this.setVisible(true);
    }
}
