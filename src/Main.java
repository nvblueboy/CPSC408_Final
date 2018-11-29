import com.google.protobuf.ByteString;

import java.util.ArrayList;

public class Main {
    static int userId;

    public static void main(String[] argv) {
        //Handle log-in here.



        //After user is logged in...
        userId = 1; //Once the user is logged in, set their user id to this.
        int choice = -1;

        while (choice != 0) {
            System.out.println("Main Menu: ");
            System.out.println("  0. Exit.");
            System.out.println("  1. Search the Database");
            System.out.println("  2. Playlist Utilities");

            choice = Input.getInt("Please type the number you'd like to go to.");


            switch (choice) {
                case 1:
                    searchSongs();
                    break;
                case 2:
                    playlistMenu();
                    break;
                case 3:
                    userMenu();
                    break;
                default: break;
            }



        }
        //Leave this code at the end to wrap up execution.
        Database.closeConnection();
        Input.closeScanner();

        System.out.println("Goodbye.");
    }

    public static void searchSongs() {
        ArrayList<SearchResult> results = Database.genericSearch(Input.getString("What search term would you like to search for? Can be artist, song, or album."));

        System.out.println("Search results: ");
        System.out.println(Output_Utilities.prettyPrint(results));
    }

    public static void playlistMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Playlist menu: ");
            System.out.println("  0. Main Menu");
            System.out.println("  1. View a playlist");
            System.out.println("  2. Find a playlist");
            System.out.println("  3. Create a playlist");
            System.out.println("  4. Add to a playlist");
            System.out.println("  5. Delete a playlist");

            choice = Input.getInt("Please type the number you'd like to go to.");

            if (choice == 1) {
                displayPlaylist();
            } else if (choice == 2) {
                searchPlaylists();
            } else if (choice == 3) {
                createPlaylist();
            } else if (choice == 4) {
                addToPlaylist();
            } else if (choice == 5) {
                deletePlaylist();
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

    public static void createPlaylist() {
        System.out.println("Created " + Database.createPlaylist(Input.getString("What is the playlist name?"), userId).toString());
    }

    public static void deletePlaylist() {
        checkForPlaylistId();
        Database.deletePlaylist(Input.getInt("What is the playlist ID you want to delete?"));
    }

    public static void checkForPlaylistId() {
        while (!Input.getBoolean("Do you know the ID of the playlist (y/n)?")) {
            searchPlaylists();
        }
    }

    public static void checkForSongId() {
        while (!Input.getBoolean("Do you know the ID of the song you're adding (y/n)?")) {
            searchSongs();
        }
    }

    public static void userMenu() {
        System.out.println("Welcome!");
        System.out.println("  1. Create new user");
        System.out.println("  2. Log in\n");

        while (true) {
            int choice = Input.getInt("Please enter your choice.");

            switch (choice) {
                case 1:
                    Database.createNewUser();
                    break;
                case 2:
                    Database.checkIfUserExists();
                    break;
                default: continue;
            }
        }
    }
}
