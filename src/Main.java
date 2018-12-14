import com.google.protobuf.ByteString;

import java.util.ArrayList;

public class Main {
    static int userId;

    public static void main(String[] argv) {
        userId = userMenu();
        int choice = -1;

        while (choice != 0) {
            System.out.println("Main Menu: ");
            System.out.println("  0. Exit.");
            System.out.println("  1. Search the Database");
            System.out.println("  2. Playlist Utilities");
            System.out.println("  3. Artist recommendations");

            choice = Input.getInt("Please type the number you'd like to go to.");


            switch (choice) {
                case 1:
                    searchSongs();
                    break;
                case 2:
                    playlistMenu();
                    break;
                case 3:
                    artistRecMenu();
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
            System.out.println("  9. Get recommendations based on a playlist");

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
                case 9:
                    getSongRecs();
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

    public static void getSongRecs() {
        checkForPlaylistId();
        ArrayList<SearchResult> results = Database.getSongRecs(Input.getInt("What is the playlist ID you'd like to get recommendations for?"));

        System.out.println(Output_Utilities.prettyPrint(results));
    }

    public static void artistRecMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\nArtist Recommendations");
            System.out.println("  0. Main menu");
            System.out.println("  1. Recommend an artist");
            System.out.println("  2. Get an artist recommendation");

            choice = Input.getInt("Please type the number you'd like to go to.");

            switch(choice) {
                case 1:
                    makeRecommendation();
                    break;
                case 2:
                    getRecommendations();
                    break;
                default: break;
            }
        }
    }

    public static void makeRecommendation() {
        System.out.println("First, tell us the first artist you want to recommend from (e.g. If you like (this one), you'll like...");
        checkForArtistId();
        int id1 = Input.getInt("What is the ID of the artist you'd like to recommend from?");
        System.out.println("Second, tell us the artist you want to recommend (e.g. If you like ... you'll like (this one)");
        checkForArtistId();
        int id2 = Input.getInt("What is the ID of the artist you'd like to recommend to?");

        Database.createArtistRec(id1, id2, userId);
    }

    public static void getRecommendations() {
        checkForArtistId();
        int id = Input.getInt("What is the ID of the artist you'd like to get recommendations for?");

        ArrayList<Artist> artists = Database.getArtistRecs(id);

        for(Artist a : artists) {
            System.out.println(a.toString());
        }
    }

    public static void checkForArtistId() {
        while(!Input.getBoolean("Do you know the ID of the artist?")) {
            searchArtists();
        }
    }

    public static void searchArtists() {
        ArrayList<Artist> artists = Database.searchArtists(Input.getString("What would you like to search for?"));

        for(Artist a : artists) {
            System.out.println(a.toString());
        }
    }

    public static int userMenu() {
        System.out.println("Welcome!");
        System.out.println("  1. Create new user");
        System.out.println("  2. Log in\n");

        while (true) {
            int choice = Input.getInt("Please enter your choice.");
            User u = new User();

            switch (choice) {
                case 1:
                    u = Database.createNewUser();
                    break;
                case 2:
                    u = Database.checkIfUserExists();
                    break;
                default: continue;
            }

            return u.getUserID();
        }
    }
}
