public class Main {
    public static void main(String[] argv) {
    Integer userId; //Once the user is logged in, set their user id to this.

        for (Artist a : Database.findArtistByName(Input.getString("What artist would you like to look up? "))) {
            System.out.println(a.toString());
        }


        //Leave this code at the end to wrap up execution.
        Database.closeConnection();

        System.out.println("Goodbye.");
    }
}
