/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: This is the Song class, which keeps track of individual song titles, artist, genres, and times and it allows you to compare two cd's
 */
public class Song implements Comparable<Song> {
	private String title;
	private String artist;
	private String genre;
	private Time time;
	private int rating;
	
	//Song constructors with different parameters
	public Song(String title,String artist, String genre, int rating, Time time)
	{
		this.setTitle(title);
		this.setArtist(artist);
		this.setGenre(genre);
		this.setRating(rating);
		this.setTime(time);
	}
	public Song (Song song)
	{
		this.title= new String(song.title);
		this.artist =  new String(song.artist);
		this.genre = new String(song.genre);
		this.time = new Time(song.time);
		this.rating = song.rating;
	}
	public String toString()
	{
		return title;
	}
	
	public int compareTo(Song obj)
	{
		return this.title.compareToIgnoreCase(obj.getTitle());
	}
	public boolean equals(Object songObject)
	{
		Song songToCompare = (Song)songObject;
		if(songToCompare == null)
			return false;
		if(this.title.equalsIgnoreCase(songToCompare.getTitle()) && 
				this.artist.equalsIgnoreCase(songToCompare.getArtist()) 
				&& this.getTime().equals(songToCompare.time)
				&& this.genre.equals(songToCompare.getGenre()) 
				&& this.rating == songToCompare.rating)
			return true;
		else
			return false;
		
	}
	//getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
