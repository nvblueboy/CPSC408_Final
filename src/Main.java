import com.google.protobuf.ByteString;

import java.util.ArrayList;

public class Main {
    public static void main(String[] argv) {
        //Handle log-in here.



        //After user is logged in...
        int userId = 1; //Once the user is logged in, set their user id to this.
        int choice = -1;

        while (choice != 0) {
            System.out.println("Main Menu: ");
            System.out.println("  0. Exit.");
            System.out.println("  1. Search the Database");
            System.out.println("  2. Playlist Utilities");

            choice = Input.getInt("Please type the number you'd like to go to.");

            if (choice == 1) {
                searchArtists(); //This function does not modify the database in any way.
            } else if (choice == 2) {
                playlistMenu();
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

    public static void playlistMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Playlist menu: ");
            System.out.println("  0. Main Menu");
            System.out.println("  1. View a playlist");

            choice = Input.getInt("Please type the number you'd like to go to.");

            if (choice == 1) {
                displayPlaylist();
            }
        }
    }

    public static void displayPlaylist() {
        ArrayList<SearchResult> results = Database.getSongsFromPlaylist(Input.getInt("Please input the playlist ID you'd like to display."));

        System.out.println(Output_Utilities.prettyPrint(results));
    }
}
