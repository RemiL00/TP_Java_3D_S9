public class Main {
    public static void main(String[] args) {
        World world = new World("data/airport-codes_no_comma.csv");
        Aeroport aeroport = new Aeroport("Paris Orly", "ORY", "France",48.716, 2.383); // Exemple avec des coordonnées fictives
        //Aeroport a = new Aeroport("Paris CDG", "CDG", "France",49.0097, 2.5479);
        //ouble distance = aeroport.calculDistance(a);
        //System.out.println("Distance : " + distance + " unités.");

        System.out.println("Found "+ world.getList().size()+" airports.");
        Aeroport paris = world.findNearest(48.866,2.316);
        world.findNearest(48.866,2.316);
        Aeroport cdg = world.findByCode("CDG");
        double distance = aeroport.calculDistance(paris);
        System.out.println(paris);
        System.out.println(distance);
        double distanceCDG = aeroport.calculDistance(cdg);
        System.out.println(cdg);
        System.out.println(distanceCDG);
    }
}