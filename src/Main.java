public class Main {
    public static void main(String[] argv) {
        System.out.println("Test");

        for (Artist a : Database.getAllArtists()) {
            System.out.println(a.toString());
        }


        //Leave this code at the end to wrap up execution.
        Database.closeConnection();
    }
}
