package Backend;

public class Song implements Comparable<Song> {
	public String name, artist, album;
	public int year;
	
	public Song() {
		name = "test";
		artist = "dude";
		album = "thing";
		year = 1;
	}
	
	public Song(String n, String ar, String al, int y) {
		name = n;
		artist = ar;
		album = al;
		year = y;
	}
	
	public int compareTo(Song s) {
		int ret = name.compareTo(s.name);
		
		if(ret != 0)
			return ret;
		else {
			ret = artist.compareTo(s.artist);
			
			if(ret != 0)
				return ret;
			else
				return year - s.year; //this is unecessary, but just in case!
		}
	}
	
	public String toString() {
		return name + " | " + artist + " | " + album + " | " + year;
	}
}
