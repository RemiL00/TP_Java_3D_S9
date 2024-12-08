import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonFlightFiller {
    private ArrayList<Flight> list = new ArrayList<>(); // Liste des vols

    public JsonFlightFiller(String jsonString, World w) {
        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    String airlineCode = result.getJsonObject("airline").getString("iata", "UNKNOWN");
                    String airlineName = result.getJsonObject("airline").getString("name", "UNKNOWN");
                    String flightNumberStr = result.getJsonObject("flight").getString("number", null);
                    int flightNumber = -1;

                    if(flightNumberStr!=null){
                        try{
                            flightNumber = Integer.parseInt(flightNumberStr);
                        }catch (NumberFormatException e){
                            System.out.println("Erreur de conversion : " +flightNumberStr+ "n'est pas un entier");
                        }
                    }
                    System.out.println("Flight number: "+flightNumber);

                    String departureIATA = result.getJsonObject("departure").getString("iata", "UNKNOWN");
                    String arrivalIATA = result.getJsonObject("arrival").getString("iata", "UNKNOWN");

                    LocalDateTime departureTime = LocalDateTime.parse(
                            result.getJsonObject("departure").getString("scheduled", "1970-01-01T00:00:00"),
                            DateTimeFormatter.ISO_DATE_TIME
                    );
                    LocalDateTime arrivalTime = LocalDateTime.parse(
                            result.getJsonObject("arrival").getString("scheduled", "1970-01-01T00:00:00"),
                            DateTimeFormatter.ISO_DATE_TIME
                    );

                    Aeroport departure = w.findByCode(departureIATA);
                    Aeroport arrival = w.findByCode(arrivalIATA);
                    System.out.println("Aeroport de départ: "+departure);
                    System.out.println("Aeroport d'arrivée: "+arrival);
                    if (departure != null && arrival != null && flightNumber > 0) {
                        Flight flight = new Flight(airlineCode, airlineName, arrivalTime, departureTime, flightNumber, departure, arrival);
                        list.add(flight);
                    } else {
                        System.out.println("Données invalides pour le vol : " + flightNumber);
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de l'analyse d'un vol : " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'analyse JSON : " + e.getMessage());
        }
    }

    // Getter pour la liste des vols
    public ArrayList<Flight> getList() {
        return list;
    }
}
