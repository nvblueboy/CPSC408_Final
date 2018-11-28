import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    int UserID;
    String Name;
    String HashedPassword;

    public User() { }

    public User(int userID, String name, String hashedPassword) {
        UserID = userID;
        Name = name;
        HashedPassword = hashedPassword;
    }

    public User(ResultSet rs) {
        if (Database.resultSetHasColumn(rs, "UserID")) {
            try {
                UserID = rs.getInt("UserID");
            } catch (SQLException ex) {
                System.out.println("User.java: Could not get UserID.");
            }
        }
        if (Database.resultSetHasColumn(rs, "Name")) {
            try {
                Name = rs.getString("Name");
            } catch (SQLException ex) {
                System.out.println("User.java: Could not get Name.");
            }
        }
        if (Database.resultSetHasColumn(rs, "HashedPassword")) {
            try {
                HashedPassword = rs.getString("HashedPassword");
            } catch (SQLException ex) {
                System.out.println("User.java: Could not get HashedPassword.");
            }
        }
    }

    public String toString() {
        return "User #" + Integer.toString(UserID) + ": " + Name;
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

    public String getHashedPassword() {
        return HashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        HashedPassword = hashedPassword;
    }
}
