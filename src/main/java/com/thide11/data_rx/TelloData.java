package com.thide11.data_rx;

public class TelloData {
    /** Attitude pitch, degree */
    private int pitch;
     /** Attitude roll, degree */
    private int roll;
    /** Attitude yaw, degree */
    private int yaw;
    /** Speed x */
    private int vgx;
    /** Speed y */
    private int vgy;
    /** Speed z */
    private int vgz;
    /** Lowest temperature, celcius degree */
    private int templ;
    /** Highest temperature, celcius degree */
    private int temph;
    /** TOF distance, cm, means the height in relation of the surface */
    private int tof;
    /** Height after starting point of takeoff, cm */
    private int height; //Drone send 'h' on DataRx
    /** Current battery percentage, % */
    private int battery; //Drone send bat on DataRx
    /** Barometer measurement, cm */
    private float baro;
    /** Motors on time */
    private int time;
    /** Acceleration x */
    private float agx;
    /** Acceleration y */
    private float agy;
    /** Acceleration z */
    private float agz;

    private void parseTelloData(String telloData) {
        String[] atributes = telloData.split(";");
        for (String data : atributes) {
            String[] atributeAndValue = data.split(":");
            //System.out.println(atributeAndValue.toString());
            if(atributeAndValue.length == 2) {
                String atribute = atributeAndValue[0];
                String value = atributeAndValue[1];
                switch (atribute) {
                    case "pitch":
                        this.pitch = Integer.parseInt(value);
                        break;
                    case "roll":
                        this.roll = Integer.parseInt(value);
                        break;
                    case "yaw":
                        this.yaw = Integer.parseInt(value);
                        break;
                    case "vgx":
                        this.vgx = Integer.parseInt(value);
                        break;
                    case "vgy":
                        this.vgy = Integer.parseInt(value);
                        break;
                    case "vgz":
                        this.vgz = Integer.parseInt(value);
                        break;
                    case "templ":
                        this.templ = Integer.parseInt(value);
                        break;
                    case "temph":
                        this.temph = Integer.parseInt(value);
                        break;
                    case "tof":
                        this.tof = Integer.parseInt(value);
                        break;
                    case "h":
                        this.height = Integer.parseInt(value);
                        break;
                    case "bat":
                        this.battery = Integer.parseInt(value);
                        break;
                    case "baro":
                        this.baro = Float.parseFloat(value);
                        break;
                    case "time":
                        this.time = Integer.parseInt(value);
                        break;
                    case "agx":
                        this.agx = Float.parseFloat(value);
                        break;
                    case "agy":
                        this.agy = Float.parseFloat(value);
                        break;
                    case "agz":
                        this.agz = Float.parseFloat(value);
                        break;
                    default:
                        System.out.println(String.format("Atributo recebido '%s' não encontrado", atribute));
                }
            }
        }
    }

    public TelloData(String telloSendedData) {
        parseTelloData(telloSendedData);
    }

    public TelloData(int pitch, int roll, int yaw, int vgx, int vgy, int vgz, int templ, int temph, int tof, int height, int battery, float baro, int time, float agx, float agy, float agz) {
        this.pitch = pitch;
        this.roll = roll;
        this.yaw = yaw;
        this.vgx = vgx;
        this.vgy = vgy;
        this.vgz = vgz;
        this.templ = templ;
        this.temph = temph;
        this.tof = tof;
        this.height = height;
        this.battery = battery;
        this.baro = baro;
        this.time = time;
        this.agx = agx;
        this.agy = agy;
        this.agz = agz;
    }

    public boolean equals(TelloData telloComparate) {
        return
            this.pitch == telloComparate.pitch
            &&
            this.roll == telloComparate.roll
            &&
            this.yaw == telloComparate.yaw
            &&
            this.vgx == telloComparate.vgx
            &&
            this.vgy == telloComparate.vgy
            &&
            this.vgz == telloComparate.vgz
            &&
            this.templ == telloComparate.templ
            &&
            this.temph == telloComparate.temph
            &&
            this.tof == telloComparate.tof
            &&
            this.height == telloComparate.height
            &&
            this.battery == telloComparate.battery
            &&
            this.baro == telloComparate.baro
            &&
            this.time == telloComparate.time
            &&
            this.agx == telloComparate.agx
            &&
            this.agy == telloComparate.agy
            &&
            this.agz == telloComparate.agz;
    }

    public int getPitch() {
        return pitch;
    }

    public int getRoll() {
        return roll;
    }

    public int getYaw() {
        return yaw;
    }

    public int getVgx() {
        return vgx;
    }

    public int getVgy() {
        return vgy;
    }

    public int getVgz() {
        return vgz;
    }

    public int getTempl() {
        return templ;
    }

    public int getTemph() {
        return temph;
    }

    public int getTof() {
        return tof;
    }

    public int getHeight() {
        return height;
    }

    public int getBattery() {
        return battery;
    }

    public float getBaro() {
        return baro;
    }

    public int getTime() {
        return time;
    }

    public float getAgx() {
        return agx;
    }

    public float getAgy() {
        return agy;
    }

    public float getAgz() {
        return agz;
    }
}
