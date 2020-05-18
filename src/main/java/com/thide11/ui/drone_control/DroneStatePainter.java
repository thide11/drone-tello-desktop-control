package com.thide11.ui.drone_control;

import com.thide11.data_rx.TelloData;
import com.thide11.ui.drone_control.DroneRcData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class DroneStatePainter implements ImageObserver {

    static Point ROOT_POSITION_DRONE_STATE = new Point(120, 150);
    static int LINES_SIZE = 120;

    static double PERCERCENT_DEGREES = 0.011111111F; // 90 / 100

    //To dray color of drone moviment
    static Color COLOR_CONTROL_DISABLE = Color.green;
    static Color COLOR_CONTROL_ENABLE = Color.red;

    private BufferedImage img;
    TelloData telloData;

    public DroneStatePainter() {
        try {
            img = ImageIO.read(new File("H:\\OneDrive - Fatec Centro Paula Souza\\programacao\\projetos_com_lingaugens_variadas\\projetosEmJava\\controladorDroneTello\\assets\\images\\drone.png"));
        } catch (IOException e) {
            System.out.println("Erro ao ler imagem");
            e.printStackTrace();
        }
    }

    private Color getConditionalColor(int value, int equals) {
        return value == equals ? COLOR_CONTROL_ENABLE : COLOR_CONTROL_DISABLE;
    }

    public void refreshDronePainter(Graphics g, TelloData telloData, DroneRcData droneRcData) {
        this.telloData = telloData;
        if(droneRcData == null) {
            g.setColor(COLOR_CONTROL_DISABLE);
            drawForward(g);
            drawBackward(g);
            drawUp(g);
            drawDown(g);
            drawLeft(g);
            drawRight(g);
        } else {
            g.setColor(getConditionalColor(droneRcData.getForward(), 1));
            drawForward(g);
            g.setColor(getConditionalColor(droneRcData.getForward(), -1));
            drawBackward(g);
            g.setColor(getConditionalColor(droneRcData.getUpDown(), 1));
            drawUp(g);
            g.setColor(getConditionalColor(droneRcData.getUpDown(), -1));
            drawDown(g);
            g.setColor(getConditionalColor(droneRcData.getLeftRight(), 1));
            drawRight(g);
            g.setColor(getConditionalColor(droneRcData.getLeftRight(), -1));
            drawLeft(g);
        }
        drawDrone(g);
    }

    private double calculateProportion(int lineSize, int degressDown) {
        return lineSize * (PERCERCENT_DEGREES * degressDown);
    }

    private void drawDrone(Graphics g) {
        g.drawImage(img, ROOT_POSITION_DRONE_STATE.x - (img.getWidth() / 2) - 10, ROOT_POSITION_DRONE_STATE.y - (img.getHeight() / 2), (ImageObserver) this);
    }

    private void drawLeft(Graphics g) {
        int percentToDown = 45;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) - proportion);
        int finalX = (int) Math.floor((startX) + (LINES_SIZE - proportion));
        g.drawLine(startX, startY, finalX, finalY);
    }

    private void drawRight(Graphics g) {
        int percentToDown = 45;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) + proportion);
        int finalX = (int) Math.floor((startX) - (LINES_SIZE - proportion));
        g.drawLine(startX, startY, finalX, finalY);
    }

    private void drawUp(Graphics g) {
        int percentToDown = 10;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) - (LINES_SIZE - proportion));
        int finalX = (int) Math.floor((startX) - proportion);
        g.drawLine(startX, startY, finalX, finalY);
    }

    private void drawDown(Graphics g) {
        int percentToDown = 80;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) + proportion);
        int finalX = (int) Math.floor((startX) + (LINES_SIZE - proportion));
        g.drawLine(startX, startY, finalX, finalY);
    }

    private void drawForward(Graphics g) {
        int percentToDown = 13;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) + proportion);
        int finalX = (int) Math.floor((startX) + (LINES_SIZE - proportion));
        g.drawLine(startX, startY, finalX, finalY);
    }

    private void drawBackward(Graphics g) {
        int percentToDown = 15;
        double proportion = calculateProportion(LINES_SIZE, percentToDown);
        int marginRootX = 0;
        int marginRootY = 0;
        int startX = marginRootX + ROOT_POSITION_DRONE_STATE.x;
        int startY = marginRootY + ROOT_POSITION_DRONE_STATE.y;
        int finalY = (int) Math.floor((startY) - proportion);
        int finalX = (int) Math.floor((startX) - (LINES_SIZE - proportion));
        g.drawLine(startX, startY, finalX, finalY);
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
