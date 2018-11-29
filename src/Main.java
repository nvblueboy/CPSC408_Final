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
            System.out.println("\n\nPlaylist menu: ");
            System.out.println("  0. Main Menu");
            System.out.println("  1. View a playlist");
            System.out.println("  2. Find a playlist");
            System.out.println("  3. Create a playlist");
            System.out.println("  4. Add to a playlist");
            System.out.println("  5. Delete a playlist");
            System.out.println("  6. Delete a song from a playlist");
            System.out.println("  7. Rename a playlist");
            System.out.println("  8. View all your playlists");

            choice = Input.getInt("Please type the number you'd like to go to.");

            switch(choice) {
                case 1:
                    displayPlaylist();
                    break;
                case 2:
                    searchPlaylists();
                    break;
                case 3:
                    createPlaylist();
                    break;
                case 4:
                    addToPlaylist();
                    break;
                case 5:
                    deletePlaylist();
                    break;
                case 6:
                    removeFromPlaylist();
                    break;
                case 7:
                    renamePlaylist();
                    break;
                case 8:
                    viewAllPlaylists();
                    break;
                default: break;
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

    public static void removeFromPlaylist() {
        checkForPlaylistId();
        int playlistId = Input.getInt("What is the playlist ID?");
        checkForSongId();
        int songId = Input.getInt("What is the song ID?");
        Database.removeSongFromPlaylist(playlistId, songId);
    }

    public static void renamePlaylist() {
        checkForPlaylistId();
        int playlistId = Input.getInt("What is the playlist ID?");
        String name = Input.getString("What would you like to rename the playlist to?");
        Database.renamePlaylist(playlistId, name);
    }

    public static void createPlaylist() {
        System.out.println("Created " + Database.createPlaylist(Input.getString("What is the playlist name?"), userId).toString());
    }

    public static void deletePlaylist() {
        checkForPlaylistId();
        Database.deletePlaylist(Input.getInt("What is the playlist ID you want to delete?"));
    }

    public static void viewAllPlaylists() {
        for (Playlist p : Database.getUserPlaylists(userId)) {
            System.out.println(p.toString());
        }
    }

    public static void checkForPlaylistId() {
        while (!Input.getBoolean("Do you know the ID of the playlist (y/n)?")) {
            searchPlaylists();
        }
    }

    public static void checkForSongId() {
        while (!Input.getBoolean("Do you know the ID of the song (y/n)?")) {
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
