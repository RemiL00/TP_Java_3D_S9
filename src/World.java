import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Aeroport> list = new ArrayList<>();

    public World(String fileName) {
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");
                //Enleve les guillemets qui separent les champs de donnees GPS.
                String[] fields =s.split(",");
                // Une bonne idee : placer un point d'arret ici pour du debuggage.
                if (fields[1].equals("large_airport")){
                    String name = fields[2];
                    String country = fields[5];
                    String iataCode = fields[9];
                    double longitude = parseDouble(fields[11]);
                    double latitude = parseDouble(fields[12]);

                    Aeroport aeroport = new Aeroport(name, iataCode, country, latitude, longitude);
                    list.add(aeroport);
                }
                s = buf.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size() - 1));
            e.printStackTrace();
        }
    }

    private double parseDouble(String value){
        return Double.parseDouble(value);
    }

    public List<Aeroport> getList() {
        return list;
    }


    public double calculDistance(Aeroport a){

        return 0;
    }
}