/*
 * class Taxi : 
 *  - child class of PublicTransport
 */
public class Taxi extends PublicTransport {
    private double farePerKm;
    private double distance;

    // constructor
    public Taxi(double baseFare, double farePerKm, double distance) {
        super(baseFare);
        super.setModel("KAKAO TAXI");
        setFarePerKm(farePerKm);
        setDistance(distance);
    }

    // getters and setters
    public double getFarePerKm() {
        return farePerKm;
    }

    public void setFarePerKm(double farePerKm) {
        this.farePerKm = farePerKm;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override // from PublicTransport
    public void calculatePayment() {
        super.setTotalFare(super.getBaseFare() + (getFarePerKm() * getDistance()));
    }
}
