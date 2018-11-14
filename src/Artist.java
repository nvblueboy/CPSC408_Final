public class Artist {
    private int ArtistID;
    private String Name;

    public Artist() {
    }

    public Artist(int artistID, String name) {
        ArtistID = artistID;
        Name = name;
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
