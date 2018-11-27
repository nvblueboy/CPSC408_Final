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
            return resultSetToList(rs);
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
            return resultSetToList(rs);

        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get artists by name.");
            System.out.println(ex.toString());
            return new ArrayList<Artist>();
        }
    }

    public static ArrayList<Artist> getAllArtists() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Artist");
            ResultSet rs = stmt.executeQuery();
            return resultSetToList(rs);
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get all artists.");
            return new ArrayList<Artist>();
        }
    }

    public static ArrayList<Artist> resultSetToList(ResultSet rs) {
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
}
