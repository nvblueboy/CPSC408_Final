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
                mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc408_final", "dbmanager", "password");
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

    public static ArrayList<Artist> getArtistsByIDs(Integer[] ids) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Artist WHERE ArtistID IN ?");
            Array array = getConnection().createArrayOf("INT", ids);
            stmt.setArray(1, array);
            ResultSet rs = stmt.executeQuery();
            return resultSetToArtistList(rs);
        } catch (SQLException ex) {
            System.out.println("Database.java: Could not get artists by IDs.");
            return new ArrayList<Artist>();
        }
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
