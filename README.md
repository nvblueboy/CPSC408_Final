### CPSC408 Final Project
#### A Playlist/Artist recommendation system for music
##### Dylan Bowman and Vince Carpino

Dylan Bowman:\
Email: dbowman06@gmail.com

Vince Carpino:\
Email: carpi111@mail.chapman.edu


References: Lecture, Stack Overflow, Caffiene


##### To setup:
- Run create_schema.sql in a MySQL Database to create the tables.
- Using a data loader of your choice (we used MySQL Workbench) to load the following files, in order:
  - Smallartists.csv
  - Smallalbums.csv
  - Smallsongs.csv
- Run create_stored_procedure.sql to create the required procedure for song recommendations.


If you're interested in using the full Musicbrainz database, download it from the Musicbrainz developer site, unzip it, drop createCSV.py into the folder with the database README, and run it using python3. Tweak the "limit" arguments on every call of readfile() to read the entire database. This will take some time.
