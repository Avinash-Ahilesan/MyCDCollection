import java.util.Comparator;
/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: Sort by Artist for songs  (thats also the function of the only method in this class)
 */
public class SortArtist implements Comparator<Song>{

	@Override
	public int compare(Song song1, Song song2) {
		// TODO Auto-generated method stub
		return song1.getArtist().compareToIgnoreCase(song2.getArtist());
	}
	

}
