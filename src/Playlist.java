public class Playlist {
    int PlaylistID;
    int UserID;
    String Name;

    public Playlist() {
    }

    public Playlist(int playlistID, int userID, String name) {
        PlaylistID = playlistID;
        UserID = userID;
        Name = name;
    }

    public int getPlaylistID() {
        return PlaylistID;
    }

    public void setPlaylistID(int playlistID) {
        PlaylistID = playlistID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
