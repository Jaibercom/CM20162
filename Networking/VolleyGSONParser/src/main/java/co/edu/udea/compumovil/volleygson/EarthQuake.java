package co.edu.udea.compumovil.volleygson;

/**
 * Created by jaiber on 22/04/16.
 */
public class EarthQuake {
    private String datetime;
    private int depth;
    private String eqid;
    private float lat;
    private float lng;
    private float magnitude;
    private String src;

    public EarthQuake(String dateTime, int depth, String eqid, float lat, float lng, float magnitude, String src) {
        datetime = dateTime;
        this.depth = depth;
        this.eqid = eqid;
        this.lat = lat;
        this.lng = lng;
        this.magnitude = magnitude;
        this.src = src;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
