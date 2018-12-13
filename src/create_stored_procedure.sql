CREATE DEFINER=`root`@`localhost` PROCEDURE `getSuggestedSongs`(IN PlId INT)
  BEGIN
    SELECT DISTINCT s.SongID, s.Name AS SongName, a.Name as AlbumName, ar.Name as ArtistName, ar.ArtistId, a.AlbumId, s.AlbumIndex
    FROM

         (SELECT ps2.SongId, COUNT(*) cnt FROM playlistsong ps1, playlistsong ps2 WHERE (ps1.PlaylistId = ps2.PlaylistId) AND (ps1.SongID != ps2.SongID) AND (ps1.SongId IN (SELECT SongID FROM PlaylistSong WHERE PlaylistId = PlId)) AND ps2.SongId NOT IN (SELECT SongID FROM PlaylistSong WHERE PlaylistId = PlId) GROUP BY ps1.SongID, ps2.SongID ORDER BY cnt DESC)

             AS T, Song s, Album a, Artist ar WHERE s.SongID = T.SongID AND s.AlbumId = a.AlbumId AND a.ArtistId = ar.ArtistId LIMIT 5;
  END