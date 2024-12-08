import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import javafx.application.Platform;

class RequestHttp implements Runnable {
    private String apiUrl;
    private World world;
    private Earth earth;

    public RequestHttp(String apiUrl, World world, Earth earth) {
        this.apiUrl = apiUrl;
        this.world = world;
        this.earth = earth;
    }

    @Override
    public void run() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            System.out.println("Réponse de l'API : " + jsonResponse);

            JsonFlightFiller jsonFlightFiller = new JsonFlightFiller(jsonResponse, world);

            for (Flight flight : jsonFlightFiller.getList()) {
                Aeroport departure = flight.getDeparture();
                if (departure != null) {
                    System.out.println("Affichage de la sphère jaune de :" + departure);
                    Platform.runLater(() -> earth.displayYellowSphere(departure));                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'interrogation de l'API : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

