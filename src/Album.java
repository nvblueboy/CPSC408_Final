public class Album {
    private int ArtistID;
    private int AlbumID;
    private String Name;

    public Album() {
    }

    public Album(int artistID, int albumID, String name) {
        ArtistID = artistID;
        AlbumID = albumID;
        Name = name;
    }

    public int getArtistID() {
        return ArtistID;
    }

    public void setArtistID(int artistID) {
        ArtistID = artistID;
    }

    public int getAlbumID() {
        return AlbumID;
    }

    public void setAlbumID(int albumID) {
        AlbumID = albumID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
