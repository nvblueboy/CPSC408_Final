import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection mysqlConnection;

    /**
     * getConnection - a singleton function to check that a single connection is live before using it.
     * @return Connection - a valid connection to the sql server.
     */
    public static Connection getConnection() {
        if (mysqlConnection == null) {
            try {
                mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc408_final?useSSL=false", "dbmanager", "password");

                // mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc408_final", "root", "Vc@rpino3");
            } catch (SQLException ex) {
                System.out.println("Database.java: getConnection failed to create a connection.");
                System.out.println(ex.toString());
            }
        }

        return mysqlConnection;
    }

    public static void closeConnection() {
        if (mysqlConnection != null) {
            try {
                getConnection().close();
                mysqlConnection = null;
            } catch (SQLException ex) {
                System.out.println("The connection could not be closed.");
            }
        }
    }

    public static boolean resultSetHasColumn(ResultSet rs, String column) {
        try {
            ResultSetMetaData md = rs.getMetaData();
            int count = md.getColumnCount();

            for (int i = 1; i < count + 1; ++i) {
                if (column.equals(md.getColumnName(i))) {
                    return true;
                }
            }
            return false;

        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get Result Set Metadata.");
            return false;
        }
    }

    public static String in_array(int amount) {
        String out = "";

        for (int i = 0; i < amount; ++i) {

            if (i > 0) {
                out = out + ",";
            }

            out = out + "?";

        }

        return "(" + out + ")";
    }

    public static ArrayList<Artist> getArtistsByIDs(Integer[] ids) {
        try {
            //Before I get yelled at for dynamically generating SQL queries, I'm doing so because there is no way to
            //  cleanly use arrays in prepared statements for an IN clause.
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Artist WHERE ArtistID IN " + in_array(ids.length));

            for (int i = 0; i < ids.length; ++i) {
                stmt.setInt(i+1, ids[i]);
            }

            ResultSet rs = stmt.executeQuery();
            return resultSetToArtistList(rs);
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get artists by IDs.");
            System.out.println(ex.toString());
            return new ArrayList<Artist>();
        }
    }

    public static ArrayList<Artist> findArtistByName(String name) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Artist WHERE Name LIKE ?");
            stmt.setString(1, "%"+name+"%");

            ResultSet rs = stmt.executeQuery();
            return resultSetToArtistList(rs);

        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get artists by name.");
            System.out.println(ex.toString());
            return new ArrayList<Artist>();
        }
    }

    public static ArrayList<SearchResult> genericSearch(String query) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT a.ArtistID, a.Name, ab.AlbumID, ab.Name, s.SongID, s.Name, s.AlbumIndex FROM artist a, album ab, song s WHERE ab.ArtistID = a.ArtistID AND s.AlbumID = ab.AlbumID AND (a.Name LIKE ? OR s.Name LIKE ? OR ab.Name LIKE ?) ORDER BY a.Name, ab.Name, AlbumIndex");
            String searchQuery = "%" + query + "%";
            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);
            stmt.setString(3, searchQuery);

            ResultSet rs = stmt.executeQuery();
            return resultSetToSearchResultList(rs);

        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get search results.");
            System.out.println(ex.toString());
            return new ArrayList<SearchResult>();
        }
    }

    public static ArrayList<Playlist> findPlaylistsByName(String query) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Playlist WHERE Name LIKE ?");
            stmt.setString(1, "%"+query+"%");

            ResultSet rs = stmt.executeQuery();
            return resultSetToPlaylistList(rs);
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not find playlists.");
            System.out.println(ex.toString());
            return new ArrayList<Playlist>();
        }
    }

    public static ArrayList<SearchResult> getSongsFromPlaylist(int playlistId) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT a.ArtistID, a.Name, ab.AlbumID, ab.Name, s.SongID, s.Name, s.AlbumIndex FROM artist a, album ab, song s, playlist p, playlistsong ps WHERE ab.ArtistID = a.ArtistID AND s.AlbumID = ab.AlbumID AND ps.PlaylistId = ? AND ps.SongID = s.SongId ORDER BY a.Name, ab.Name, AlbumIndex");
            stmt.setInt(1, playlistId);

            ResultSet rs = stmt.executeQuery();
            return resultSetToSearchResultList(rs);

        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get playlist songs.");
            System.out.println(ex.toString());
            return new ArrayList<SearchResult>();
        }
    }

    public static void addSongToPlaylist(int playlistId, int songId) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO playlistsong (PlaylistId, SongID) VALUES (?, ?)");
            stmt.setInt(1, playlistId);
            stmt.setInt(2, songId);

            stmt.execute();
            return;
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not add song to playlist.");
            System.out.println(ex.toString());
            return;
        }
    }

    public static void createPlaylist() {

    }


    public static ArrayList<Artist> getAllArtists() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Artist");
            ResultSet rs = stmt.executeQuery();
            return resultSetToArtistList(rs);
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get all artists.");
            return new ArrayList<Artist>();
        }
    }

    public static ArrayList<Artist> resultSetToArtistList(ResultSet rs) {
        ArrayList<Artist> artistList = new ArrayList<Artist>();

        try {
            while (rs.next()) {
                Artist a = new Artist(rs);
                artistList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not convert ResultSet to ArrayList.");
        }

        return artistList;
    }

    public static ArrayList<Playlist> resultSetToPlaylistList(ResultSet rs) {
        ArrayList<Playlist> playlistList = new ArrayList<Playlist>();

        try {
            while (rs.next()) {
                Playlist p = new Playlist(rs);
                playlistList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not convert ResultSet to ArrayList.");
        }

        return playlistList;
    }


    public static ArrayList<SearchResult> resultSetToSearchResultList(ResultSet rs) {
        ArrayList<SearchResult> resultList = new ArrayList<SearchResult>();

        try {
            while (rs.next()) {
                SearchResult r = new SearchResult(rs);
                resultList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not convert ResultSet to ArrayList.");
        }

        return resultList;
    }
      
   public static ArrayList<User> resultSetToUserList(ResultSet rs) {
        ArrayList<User> userList = new ArrayList<>();

        try {
            while (rs.next()) {
                User u = new User(rs);
                userList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not convert ResultSet to ArrayList.");
        }
        return userList;
    }

    public static ArrayList<User> getAllUsers() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM User");
            ResultSet rs = stmt.executeQuery();
            return resultSetToUserList(rs);
        } catch (Exception e) {
            System.out.println("Database.java: Could not get all users.");
            return new ArrayList<User>();
        }
    }

    public static boolean validateUser(String name, String hashedPass) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM User WHERE Name=? AND HashedPassword=?");
            stmt.setString(1, name);
            stmt.setString(2, hashedPass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                System.out.println("Database.java: Invalid user credentials.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database.java: Could not validate user data.");
            return false;
        }

        return true;
    }
}
