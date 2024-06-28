/*
 * class Train :
 *  - child class of PublicTransport
 */
public class Train extends PublicTransport {

    Station stationInfo = new Station();

    // constructor
    public Train (double baseFare, double farePerStation, int nStation) {
        super(baseFare);
        super.setModel("KORAIL");
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
