/*
 * class Bus:
 *  - child class of PublicTransport
 */
public class Bus extends PublicTransport {

    Station stationInfo = new Station();

    // constructor
    public Bus(double baseFare, double farePerStation, int nStation) {
        super(baseFare);
        super.setModel("KORBUS");
        stationInfo.setFarePerStation(farePerStation);
        stationInfo.setNStation(nStation);
    }

    @Override // from PublicTransport
    public void calculatePayment() {
        if (stationInfo.getNStation() <= 5)
            super.setTotalFare(super.getBaseFare());
        else
            super.setTotalFare(super.getBaseFare() + (stationInfo.getNStation() - 5) * stationInfo.getFarePerStation());
    }
}
