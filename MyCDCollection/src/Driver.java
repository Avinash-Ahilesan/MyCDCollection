import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
/*
 * Name: Avinash
 * Date: April 30th, 2018
 * Description: This is the main driver class, which has the main menu frame and also methods relating to the arraylist containing the main CD
 */
public class Driver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	private static ArrayList<CD> MyCollection = new ArrayList<CD>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Driver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(10, 10, 10, 10));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JButton accessCDList = new JButton("Accessing your CD List");
		accessCDList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Submenu1 dialog = new Submenu1();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModal(true);
					dialog.setVisible(true);
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(accessCDList);

		JButton accessCD = new JButton("Accessing within a particular CD ");
		accessCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MyCollection.size() == 0)
					JOptionPane.showMessageDialog(frame, "You must add atleast one CD before you can enter this menu!");
				else{
					CD cd1 = (CD)JOptionPane.showInputDialog(
							frame,
							"Select the CD you want to edit:",
									"Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null,
									MyCollection.toArray(),
									(Object)MyCollection.get(0));

					if(cd1 == null)
						return;

					try {
						SubMenu2 dialog = new SubMenu2(cd1);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setModal(true);
						dialog.setVisible(true);
					} catch (Exception x) {
						x.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(accessCD);

		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(exit);
		frame.setBounds(100, 100, 351, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	 * Parameters: CD obj to add
	 * Return Type: void
	 * Description: adds CD to cd list
	 */
	public static void addCD(CD obj)
	{
		MyCollection.add(obj);
	}
	/*
	 * Parameters: CD obj
	 * Return Type: void
	 * Description: removes CD from cd list
	 */
	public static void removeCD(CD Obj)
	{
		MyCollection.remove(Obj);
	}
	public static ArrayList<CD> getCDArray()
	{
		return MyCollection;
	}
	/*
	 * Parameters: String inputFile
	 * Return Type: void
	 * Description: inputs CD's to the cd collection in this class
	 */
	public static void inputCDInfo(String inputFile)
	{
		try{
			BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
			String CDTitle;
			CDTitle = fileReader.readLine();
			int numOfSongs= Integer.parseInt(fileReader.readLine());
			CD newCD = new CD(CDTitle, numOfSongs);
			for(int i = 0; i < numOfSongs; i++)
			{
				String title = fileReader.readLine();
				String artist = fileReader.readLine();
				String genre = fileReader.readLine();
				int rating = Integer.parseInt(fileReader.readLine());
				String time = fileReader.readLine();
				Song song = new Song(title, artist, genre, rating, new Time(time));
				newCD.addSong(song);
			}

			Driver.addCD(newCD);
			Collections.sort(MyCollection);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not found!", "IO Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Parameters: CD cd to create sub-cd form, int start which is start index, and int end which is end index
	 * Return Type: void
	 * Description: adds song to songlist
	 */
	public static void createSubCD(CD cd, int start, int end)
	{
		CD subCD = new CD("sub" + cd.getTitle(), end-start);
		for(int count = start; count < end; count++)
		{
			subCD.addSong(cd.getSongs().get(count));
		}
		MyCollection.add(subCD);
	}
}
