public class Recommendation {
    int FromArtist;
    int ToArtist;
    int UserID;

    public Recommendation() { }

    public Recommendation(int fromArtist, int toArtist, int userID) {
        FromArtist = fromArtist;
        ToArtist = toArtist;
        UserID = userID;
    }

    public int getFromArtist() {
        return FromArtist;
    }

    public void setFromArtist(int fromArtist) {
        FromArtist = fromArtist;
    }

    public int getToArtist() {
        return ToArtist;
    }

    public void setToArtist(int toArtist) {
        ToArtist = toArtist;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
