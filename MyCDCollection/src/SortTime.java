import java.util.Comparator;
/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: Sort by Time for songs (thats also function of the only method)
 */
public class SortTime implements Comparator<Song>{

	@Override
	public int compare(Song song1, Song song2) {
		// TODO Auto-generated method stub
		return song1.getTime().compareTo(song2.getTime());
	}

}
