import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchResult {
    private int SongID;
    private String Song_Name;
    private int AlbumID;
    private String Album_Name;
    private int ArtistID;
    private String Artist_Name;
    private int AlbumIndex;

    public SearchResult(ResultSet rs) {
        if (Database.resultSetHasColumn(rs, "SongID")) {
            try {
                SongID = rs.getInt("SongID");
            } catch (SQLException ex) {
                System.out.println("SearchResult.java: Could not get SongID.");
            }
        }


        try {
            Song_Name = rs.getString("SongName");
        } catch (SQLException ex) {
            System.out.println("SearchResult.java: Could not get Song_Name.");
        }


        if (Database.resultSetHasColumn(rs, "AlbumID")) {
            try {
                AlbumID = rs.getInt("AlbumID");
            } catch (SQLException ex) {
                System.out.println("SearchResult.java: Could not get AlbumID.");
            }
        }

        try {
            Album_Name = rs.getString("AlbumName");
        } catch (SQLException ex) {
            System.out.println("SearchResult.java: Could not get Album_Name.");
        }


        if (Database.resultSetHasColumn(rs, "ArtistID")) {
            try {
                ArtistID = rs.getInt("ArtistID");
            } catch (SQLException ex) {
                System.out.println("SearchResult.java: Could not get ArtistID.");
            }
        }


        try {
            Artist_Name = rs.getString("ArtistName");
        } catch (SQLException ex) {
            System.out.println("SearchResult.java: Could not get Artist_Name.");
        }


        if (Database.resultSetHasColumn(rs, "AlbumIndex")) {
            try {
                AlbumIndex = rs.getInt("AlbumIndex");
            } catch (SQLException ex) {
                System.out.println("SearchResult.java: Could not get AlbumIndex.");
            }
        }


    }

    @Override
    public String toString() {
        return Song_Name + ", by " + Artist_Name + ", #"+Integer.toString(AlbumIndex) +" on " + Album_Name;
    }

    public int getSongID() {
        return SongID;
    }

    public void setSongID(int songID) {
        SongID = songID;
    }

    public String getSong_Name() {
        return Song_Name;
    }

    public void setSong_Name(String song_Name) {
        Song_Name = song_Name;
    }

    public int getAlbumID() {
        return AlbumID;
    }

    public void setAlbumID(int albumID) {
        AlbumID = albumID;
    }

    public String getAlbum_Name() {
        return Album_Name;
    }

    public void setAlbum_Name(String album_Name) {
        Album_Name = album_Name;
    }

    public int getArtistID() {
        return ArtistID;
    }

    public void setArtistID(int artistID) {
        ArtistID = artistID;
    }

    public String getArtist_Name() {
        return Artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        Artist_Name = artist_Name;
    }

    public int getAlbumIndex() {
        return AlbumIndex;
    }

    public void setAlbumIndex(int albumIndex) {
        AlbumIndex = albumIndex;
    }
}
