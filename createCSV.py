def readfile(filename, limit=0, filter_function=None, index=0):
	out_map = {}
	fileHandle = open(filename, "r")
	lines = fileHandle.readlines(limit)
	fileHandle.close()
	if limit != 0:
		lines = lines[:limit]
	for line in lines:
		spl = line.replace("\n","").split("\t")
		if (filter_function == None):
			filter_function = lambda x: True
		if (filter_function(spl)):
			lineid = spl[index]
			out_map[lineid] = spl

	return out_map


print("reading artists")
artists = readfile("./mbdump/artist", 000)
print("found "+str(len(artists))+" artists.")

print("reading tracks")
tracks = readfile("./mbdump/track", 1000000000)
print("found "+str(len(tracks))+" tracks.")

print("reading mediums")
mediums = readfile("./mbdump/medium", 00000000)
print("found "+str(len(mediums))+" mediums.")

print("reading releases")
releases = readfile("./mbdump/release", 00000000)
print("found "+str(len(releases))+" releases.")

print("reading recordings")
recordings = readfile("./mbdump/recording", 100000000)
print("found "+str(len(recordings))+" recordings.")

print("reading artist credit names.")
artist_credit_name = readfile("./mbdump/artist_credit_name", 00000000)
print("found "+str(len(artist_credit_name))+ " artist credit names.")

output = ["song_id\tsong_name\tartist_id\tartist_name\ttrack_number\talbum_name\talbum_id"]

for track_id in tracks.keys():
	track = tracks[track_id]
	r_id = track[2]
	#print(recordings[r_id])
	if (r_id in recordings):
		artist_credit_id = recordings[r_id][3]
		#print(artist_credit_id)
		if (artist_credit_id in artist_credit_name):
			acn = artist_credit_name[artist_credit_id]
			recording = recordings[r_id]

			if (acn[2] in artists):
				artist = artists[acn[2]]
				
				#We have recording (with name at index 2) and artist(name at index 2)
				#
				if (track[3] in mediums):
					medium = mediums[track[3]]

					if medium[1] in releases:
						release = releases[medium[1]]

						output.append(recording[0]+"\t"+recording[2]+"\t"+artist[0]+"\t"+artist[2]+"\t"+track[4]+"\t"+release[2]+"\t"+release[0])


out_str = "\n".join(output)

fileHandle = open("output.tsv","w")
fileHandle.write(out_str)
fileHandle.close()
			