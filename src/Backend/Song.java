package Backend;

public class Song implements Comparable<Song> {
	public String name, artist, album;
	public int year;
	
	public Song() {
		name = "";
		artist = "";
		album = "N/A";
		year = 0;
	}
	
	public Song(String n, String ar, String al, int y) {
		name = n;
		artist = ar;
		album = al;
		year = y;
	}
	
	public Song(String n, String ar) {
		// TODO Auto-generated constructor stub
		name = n;
		artist = ar;
		album = "N/A";
		year = 0;
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
	
	public boolean equals(Object o) {
		if(!(o instanceof Song))
			return false;
		
		Song s = (Song)o;
		
		return (name.equals(s.name)) && (artist.equals(s.artist));
	}
	
	public String toString() {
		
		return name + " | " + artist;
	}
}
