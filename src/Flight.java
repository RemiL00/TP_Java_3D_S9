import java.time.LocalDateTime;

public class Flight {
    String airlineCode;
    String airlineName;
    LocalDateTime arrivalTime;
    LocalDateTime departureTime;
    int number;
    Aeroport departure;
    Aeroport arrival;

    public Flight(String airlineCode, String airlineName, LocalDateTime arrivalTime, LocalDateTime departureTime, int number, Aeroport departure, Aeroport arrival) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Aeroport getDeparture() {
        return departure;
    }

    public Aeroport getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airlineCode='" + airlineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", number=" + number +
                ", departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
