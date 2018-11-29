import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Playlist(ResultSet rs) {
        if (Database.resultSetHasColumn(rs, "PlaylistID")) {
            try {
                PlaylistID = rs.getInt("PlaylistID");
            } catch (SQLException ex) {
                System.out.println("Artist.java: Could not get ArtistID.");
            }
        }
        if (Database.resultSetHasColumn(rs, "Name")) {
            try {
                Name = rs.getString("Name");
            } catch (SQLException ex) {
                System.out.println("Artist.java: Could not get Name.");
            }
        }
        if (Database.resultSetHasColumn(rs, "UserID")) {
            try {
                UserID = rs.getInt("UserID");
            } catch (SQLException ex) {
                System.out.println("Artist.java: Could not get ArtistID.");
            }
        }
    }

    public String toString() {
        return Integer.toString(PlaylistID) + ", "+Name;
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
