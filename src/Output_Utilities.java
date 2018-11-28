import java.util.ArrayList;

public class Output_Utilities {

    public static String prettyPrint(ArrayList<SearchResult> input) {
        int song_id_length = "Song ID".length();
        int song_name_length = "Song Name".length();
        int album_name_length = "Album Name".length();
        int artist_name_length = "Artist Name".length();
        int album_index_length = "Album Index".length();

        // each column in a table needs to be as wide as it's widest row.
        for (SearchResult result : input) {
            if (Integer.toString(result.getSongID()).length() > song_id_length) {
                song_id_length = Integer.toString(result.getSongID()).length();
            }

            if (result.getSong_Name().length() > song_name_length) {
                song_name_length = result.getSong_Name().length();
            }

            if (result.getAlbum_Name().length() > album_name_length) {
                album_name_length = result.getAlbum_Name().length();
            }

            if (result.getArtist_Name().length() > artist_name_length) {
                artist_name_length = result.getArtist_Name().length();
            }

            if (Integer.toString(result.getAlbumIndex()).length() > album_index_length) {
                album_index_length = Integer.toString(result.getAlbumIndex()).length();
            }
        }

        String header = pad("Song ID", song_id_length) + "|";
        header = header + pad("Song Name", song_name_length) + "|";
        header = header + pad("Album Name", album_name_length) + "|";
        header = header + pad("Artist Name", artist_name_length)+ "|";
        header = header + pad("Album Index", album_index_length);

        String output = header;

        for (SearchResult result : input) {
            String line = "\n" + pad(Integer.toString(result.getSongID()), song_id_length) + "|";
            line = line + pad(result.getSong_Name(), song_name_length) + "|";
            line = line + pad(result.getAlbum_Name(), album_name_length) + "|";
            line = line + pad(result.getArtist_Name(), artist_name_length)+ "|";
            line = line + pad(Integer.toString(result.getAlbumIndex()), album_index_length);
            output = output + line;
        }

        return output;
    }

    public static String pad(String s, int length) {
        return " "+ s + multiply(" ", length - s.length()) + " ";
    }

    public static String multiply(String s, int amount) {
        String output = "";

        for (int i = 0; i < amount; ++i) {
            output = output + s;
        }

        return output;
    }
}
