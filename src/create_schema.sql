CREATE TABLE Artist (
	ArtistID int NOT NULL AUTO_INCREMENT,
	Name varchar(128) NOT NULL,
	PRIMARY KEY (ArtistID)
);

CREATE TABLE Album (
	ArtistID int NOT NULL,
	Name varchar(256) NOT NULL,
	AlbumID int NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (AlbumID)
);

CREATE TABLE Recommendation (
	FromArtist int NOT NULL,
	ToArtist int NOT NULL,
	UserID int NOT NULL,
	PRIMARY KEY (FromArtist,ToArtist)
);

CREATE TABLE User (
	UserID int NOT NULL AUTO_INCREMENT,
	Name varchar(64) NOT NULL,
	HashedPassword char(64) NOT NULL,
	PRIMARY KEY (UserID)
);

CREATE TABLE Song (
	SongID int NOT NULL AUTO_INCREMENT,
	AlbumID int NOT NULL,
	AlbumIndex int NOT NULL,
	Name varchar(256) NOT NULL,
	PRIMARY KEY (SongID)
);

CREATE TABLE Playlist (
	PlaylistID int NOT NULL AUTO_INCREMENT,
	UserID int NOT NULL,
	Name varchar(256) NOT NULL,
	PRIMARY KEY (PlaylistID)
);

CREATE TABLE PlaylistSong (
	PlaylistId int NOT NULL,
	SongID int NOT NULL,
	PRIMARY KEY (PlaylistId,SongID)
);

ALTER TABLE Album ADD CONSTRAINT Album_fk0 FOREIGN KEY (ArtistID) REFERENCES Artist(ArtistID);

ALTER TABLE Recommendation ADD CONSTRAINT Recommendation_fk0 FOREIGN KEY (FromArtist) REFERENCES Artist(ArtistID);

ALTER TABLE Recommendation ADD CONSTRAINT Recommendation_fk1 FOREIGN KEY (ToArtist) REFERENCES Artist(ArtistID);

ALTER TABLE Recommendation ADD CONSTRAINT Recommendation_fk2 FOREIGN KEY (UserID) REFERENCES User(UserID);

ALTER TABLE Song ADD CONSTRAINT Song_fk0 FOREIGN KEY (AlbumID) REFERENCES Album(AlbumID);

ALTER TABLE Playlist ADD CONSTRAINT Playlist_fk0 FOREIGN KEY (UserID) REFERENCES User(UserID);

ALTER TABLE PlaylistSong ADD CONSTRAINT PlaylistSong_fk0 FOREIGN KEY (PlaylistId) REFERENCES Playlist(PlaylistID);

ALTER TABLE PlaylistSong ADD CONSTRAINT PlaylistSong_fk1 FOREIGN KEY (SongID) REFERENCES Song(SongID);

