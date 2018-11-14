public class PlaylistSong {
    int PlaylistID;
    int SongID;

    public PlaylistSong() {
    }

    public PlaylistSong(int playlistID, int songID) {
        PlaylistID = playlistID;
        SongID = songID;
    }

    public int getPlaylistID() {
        return PlaylistID;
    }

    public void setPlaylistID(int playlistID) {
        PlaylistID = playlistID;
    }

    public int getSongID() {
        return SongID;
    }

    public void setSongID(int songID) {
        SongID = songID;
    }
}
