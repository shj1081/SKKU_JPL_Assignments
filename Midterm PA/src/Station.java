/*
 * class Station
 *  - used by Bus and Train for calculating fare
 */
public class Station {
    private double farePerStation;
    private int nStation;

    // getters and setters
    public double getFarePerStation() {
        return farePerStation;
    }
    public void setFarePerStation(double farePerStation) {
        this.farePerStation = farePerStation;
    }
    public int getNStation() {
        return nStation;
    }
    public void setNStation(int nStation) {
        this.nStation = nStation;
    }
}
