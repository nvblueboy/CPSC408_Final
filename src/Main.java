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
                searchSongs(); //This function does not modify the database in any way.
            } else if (choice == 2) {
                playlistMenu();
            }

        }
        //Leave this code at the end to wrap up execution.
        Database.closeConnection();
        Input.closeScanner();

        System.out.println("Goodbye.");
    }

    public static void searchSongs() {
        ArrayList<SearchResult> results = Database.genericSearch(Input.getString("What search term would you like to search for? Can be artist, song, or album."));

        System.out.println("Search results: ");1
        System.out.println(Output_Utilities.prettyPrint(results));
    }

    public static void playlistMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Playlist menu: ");
            System.out.println("  0. Main Menu");
            System.out.println("  1. View a playlist");
            System.out.println("  2. Find a playlist");
            System.out.println("  3. Add to a playlist");

            choice = Input.getInt("Please type the number you'd like to go to.");

            if (choice == 1) {
                displayPlaylist();
            } else if (choice == 2) {
                searchPlaylists();
            } else if (choice == 3) {
                addToPlaylist();
            }
        }
    }

    public static void displayPlaylist() {
        ArrayList<SearchResult> results = Database.getSongsFromPlaylist(Input.getInt("Please input the playlist ID you'd like to display."));

        System.out.println(Output_Utilities.prettyPrint(results));
    }

    public static void searchPlaylists() {
        ArrayList<Playlist> results = Database.findPlaylistsByName(Input.getString("What search term would you like to look for?"));

        for (Playlist p : results) {
            System.out.println(p.toString());
        }
    }

    public static void addToPlaylist() {
        checkForPlaylistId();
        int playlistId = Input.getInt("What is the playlist ID?");
        checkForSongId();
        int songId = Input.getInt("What is the song ID?");
        Database.addSongToPlaylist(playlistId, songId);
    }

    public static void checkForPlaylistId() {
        while (!Input.getBoolean("Do you know the ID of the playlist you're adding to (y/n)?")) {
            searchPlaylists();
        }
    }

    public static void checkForSongId() {
        while (!Input.getBoolean("Do you know the ID of the song you're adding (y/n)?")) {
            searchSongs();
        }
    }
}
