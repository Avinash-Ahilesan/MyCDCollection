import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/*
 * Name: Avinash
 * Date: April 30th, 2018
 * Description: The CD class includes methods to access and add/remove songs in a CD, keep track of CD time, etc. This class actually includes a compareTo method
 *				for the purpose of displaying the CD titles in alphabetical order
 */
public class CD  implements Comparable<CD>{
	private Time totalTime;
	private String title;
	private int numOfSongs;
	private boolean sorted = false;
	
	//getters and setters for sorted 
	public boolean isSorted() {
		return sorted;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	private ArrayList<Song> songs = new ArrayList<Song>();
	private String lastSortedMethod = "";

	//getter and setter for songs arraylist
	public ArrayList<Song> getSongs()
	{
		return songs;
	}
	
	//getters and setters for lastSortedMethod
	public String getLastSortedMethod() {
		return lastSortedMethod;
	}
	public void setLastSortedMethod(String lastSortedMethod) {
		this.lastSortedMethod = lastSortedMethod;
	}



	//constructor
	public CD(String title, int numOfSongs)
	{
		this.setTitle(title);
		this.numOfSongs = numOfSongs;
		totalTime = new Time("00:00");
	}
	public CD(CD obj)
	{
		//when creating duplicate objects, you can use this constructor to make a completely new object
		this.title = new String(obj.title);
		this.numOfSongs = obj.numOfSongs;
		this.lastSortedMethod = new String(obj.lastSortedMethod);
		this.sorted = obj.sorted;
		this.totalTime = new Time(obj.totalTime);
		for(Song s: obj.songs)
		{
			songs.add(new Song(s));
		}

	}

	/*
	 * Parameters: CD cd2 which it compares with
	 * Return Type: ArrayList<Song> which returns an arraylist of songs that it finds in common
	 * Description: Compares two cd's and returns which songs are in common
	 */
	public  ArrayList<Song> compareSongs(CD cd2)
	{
		if(this.songs.size() < cd2.songs.size() || this.songs.size() == cd2.songs.size())
		{
			// i tried to make this efficient, by sorting the smaller list then binary searching for each object in the larger list
			if(!sorted)
			{
				Collections.sort(songs);
				lastSortedMethod = "title";	
				sorted = true;
				return searchForCommon(songs, cd2.songs, "title");
			}
			else if(sorted == true && lastSortedMethod.equals("title"))
				return searchForCommon(songs, cd2.songs, "title");
			else if(sorted == true &&  lastSortedMethod.equals("artist"))
				return searchForCommon(songs, cd2.songs, "artist");
			else if(sorted == true &&  lastSortedMethod.equals("time"))
				return searchForCommon(songs, cd2.songs, "time");
		}
		else if(this.songs.size() > cd2.songs.size())
		{
			if(cd2.sorted == false)
			{
				cd2.sortByTitle();
				cd2.lastSortedMethod = "title";	
				cd2.sorted = true;
				return searchForCommon(cd2.songs, songs, "title");
			}
			else if(cd2.sorted == true && cd2.lastSortedMethod == "title")
				return searchForCommon(songs, songs, "title");
			else if(cd2.sorted == true && cd2.lastSortedMethod == "artist")
				return searchForCommon(cd2.songs, songs, "artist");
			else if(cd2.sorted && cd2.lastSortedMethod == "time")
				return searchForCommon(cd2.songs, songs, "time");
		}
		return songs;	
	}
	/*
	 * Parameters: ArrayList<Song> sortedSongList is the first parameter (must be sorted by an allowed sorting method), ArrayList<Song> songList is the second list,
	 * 				String sortMethod indicates how the array is sorted
	 * Return Type: ArrayList<Song>
	 * Description: searches through two songlists, and see's if there are songs in common
	 */
	private ArrayList<Song> searchForCommon(ArrayList<Song> sortedSongList, ArrayList<Song> songList, String sortMethod)
	{
		ArrayList<Song> songsInCommon = new ArrayList<Song>();
		for(Song sg: songList)
		{
			int i = -1;
			if(sortMethod == "title")
				i = Collections.binarySearch(sortedSongList, sg);
			else if(sortMethod == "artist")
				i = Collections.binarySearch(sortedSongList, sg, new SortArtist());
			else if(sortMethod == "time")
				i = Collections.binarySearch(sortedSongList, sg, new SortTime());
			if(i > -1 && sg.equals(sortedSongList.get(i)))
				songsInCommon.add(sg);
		}
		return songsInCommon;
	}
	
	/*
	 * Parameters: Song song to add to list
	 * Return Type: void
	 * Description: adds song to songlist
	 */
	public void addSong(Song song)
	{
		songs.add(song);
		totalTime.add(song.getTime());
		if(songs.size() > numOfSongs)
			numOfSongs++;
		sorted = false;
		lastSortedMethod = "";
	}
	/*
	 * Parameters: int index of the song to remove from
	 * Return Type: void
	 * Description: remove song from list
	 */
	public void removeSong(int index)
	{
		Song mainSong = new Song(songs.get(index));
		if(sorted == true)
		{
			try{
				while( index <= songs.size() && index>-1 && songs.get(index).equals(mainSong) ){	//since when you remove an object it shifts everything when the array is sorted, this works
					totalTime.subtract(songs.get(index).getTime());
					songs.remove(index);
					numOfSongs--;
				}
			}
			catch(Exception e)
			{

			}
		}
		else
		{
			Collections.sort(songs);
			sorted=true;
			lastSortedMethod = "title";
			int newIndex = Collections.binarySearch(songs, mainSong);	
			try{
				while(newIndex < songs.size() && newIndex > -1 && songs.get(newIndex).equals(mainSong)){
					totalTime.subtract(songs.get(newIndex).getTime());
					songs.remove(newIndex);
					numOfSongs--;
				}
			}
			catch(Exception e)
			{

			}
		}


	}
	//sort methods
	public void sortByTitle()
	{
		Collections.sort(songs);
		lastSortedMethod = "title";	
		sorted = true;
	}
	public void sortByArtist()
	{
		Collections.sort(songs, new SortArtist());
		lastSortedMethod = "artist";
		sorted = true;
	}
	public void sortByTime()
	{
		Collections.sort(songs, new SortTime());
		lastSortedMethod = "time";
		sorted = true;
	}
	public String toString()
	{
		return title;
	}
	//getters and setters
	public Time getTime()
	{
		return totalTime;
	}
	public void displaySongList()
	{

	}
	public void displaySongInfo(Song song)
	{

	}

	public int getNumOfSongs() {
		return numOfSongs;
	}

	public void setNumOfSongs(int numOfSongs) {
		this.numOfSongs = numOfSongs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(CD cdObject) {
		// TODO Auto-generated method stub
		return this.title.compareToIgnoreCase(cdObject.getTitle());
	}


}
