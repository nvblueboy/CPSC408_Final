import com.google.protobuf.ByteString;

import java.util.ArrayList;

public class Main {
    public static void main(String[] argv) {
        //Handle log-in here.



        //After user is logged in...
        int userId; //Once the user is logged in, set their user id to this.
        int choice = -1;

        while (choice != 0) {
            System.out.println("Main Menu: ");
            System.out.println("  0. Exit.");
            System.out.println("  1. Search the Database");


            choice = Input.getInt("Please type the number you'd like to go to.");

            if (choice == 1) {
                searchArtists(); //This function does not modify the database in any way.
            }

        }
        //Leave this code at the end to wrap up execution.
        Database.closeConnection();

        System.out.println("Goodbye.");
    }

    public static void searchArtists() {
        ArrayList<SearchResult> results = Database.genericSearch(Input.getString("What search term would you like to search for? Can be artist, song, or album."));

        System.out.println(Output_Utilities.prettyPrint(results));
    }
}
