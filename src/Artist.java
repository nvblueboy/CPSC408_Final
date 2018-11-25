import java.sql.ResultSet;
import java.sql.*;

public class Artist {
    private int ArtistID;
    private String Name;

    public Artist() {
    }

    public Artist(int artistID, String name) {
        ArtistID = artistID;
        Name = name;
    }

    public Artist(ResultSet rs) {
        if (Database.resultSetHasColumn(rs, "ArtistID")) {
            try {
                ArtistID = rs.getInt("ArtistID");
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
    }

    public String toString() {
        return "Artist #"+Integer.toString(ArtistID)+": "+Name;
    }

    public int getArtistID() {
        return ArtistID;
    }

    public void setArtistID(int artistID) {
        ArtistID = artistID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
