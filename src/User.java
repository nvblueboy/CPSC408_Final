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
