public class Song {
    private int SongID;
    private int AlbumID;
    private int AlbumIndex;
    private String Name;

    public Song() {
    }

    public Song(int songID, int albumID, int albumIndex, String name) {
        SongID = songID;
        AlbumID = albumID;
        AlbumIndex = albumIndex;
        Name = name;
    }

    public int getSongID() {
        return SongID;
    }

    public void setSongID(int songID) {
        SongID = songID;
    }

    public int getAlbumID() {
        return AlbumID;
    }

    public void setAlbumID(int albumID) {
        AlbumID = albumID;
    }

    public int getAlbumIndex() {
        return AlbumIndex;
    }

    public void setAlbumIndex(int albumIndex) {
        AlbumIndex = albumIndex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
