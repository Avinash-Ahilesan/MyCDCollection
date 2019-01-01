import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: This class is for the Submenu 2
 */
public class SubMenu2 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static CD cd;
	private JTextField txtSongTitle;
	private JTextField txtArtistName;
	private JTextField txtGenre;
	private JTextField txtRating;
	private JTextField txtTime;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	/*
	 * Parameters: CD mainCD, which is the CD that the menu alters
	 * Return Type: none
	 * Description: constructor for sub menu 2, which alters the CD that's passed to it. This constructor creates all jframe objects and also has all actionlisteners
	 */
	public SubMenu2(CD mainCD) {
		cd = mainCD;
		setBounds(100, 100, 547, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[449.00px,grow]", "[432.00px,grow][grow]"));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			JComboBox<Song> comboBoxSong1 = new JComboBox<Song>();
			JComboBox<Song> comboBoxSong2 = new JComboBox<Song>();
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent changeEvent) {
					JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
					int index = sourceTabbedPane.getSelectedIndex();
					if(sourceTabbedPane.getTitleAt(index).equals("Display Information on Particular Song")){
						updateComboBoxWithList(comboBoxSong1);
					}
					else if(sourceTabbedPane.getTitleAt(index).equals("Remove Song"))
					{
						updateComboBoxWithList(comboBoxSong2);
					}
				}
			});
			contentPanel.add(tabbedPane, "cell 0 0,grow");
			{
				JPanel panel = new JPanel();
				JTextArea txtSongList = new JTextArea();
				tabbedPane.addTab("Display All Songs", null, panel, null);
				panel.setLayout(new MigLayout("", "[497.00,grow]", "[grow][][][][][][][][][][][]"));
				{
					panel.add(txtSongList, "cell 0 0 1 11,grow");
				}
				{
					JButton btnDisplaySongs = new JButton("Display All Songs");
					btnDisplaySongs.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//list songs
							txtSongList.setText("");
							for(Song sg: cd.getSongs())
								txtSongList.append(sg.getTitle() + "\n");
						}
					});
					panel.add(btnDisplaySongs, "cell 0 11,grow");
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Display Information on Particular Song", null, panel, null);
				panel.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
				{

					panel.add(comboBoxSong1, "cell 0 0,growx");
				}
				JTextArea textArea = new JTextArea();
				{
					JButton btnDisplayInformationOn = new JButton("Display Information On Song");
					btnDisplayInformationOn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//list particular song info
							Song sg = (Song)comboBoxSong1.getSelectedItem();
							textArea.setText("");
							textArea.append("Song Title: " + sg.getTitle() + "\n");
							textArea.append("Song Artist Name: " + sg.getArtist()+ "\n");
							textArea.append("Song Genre: " + sg.getGenre()+ "\n") ;
							textArea.append("Song Rating: " + sg.getRating()+ "\n");
							textArea.append("Time Duration: " + sg.getTime()+ "\n");
						}
					});
					panel.add(btnDisplayInformationOn, "cell 0 1,growx");
				}
				{
					panel.add(textArea, "cell 0 2,grow");
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Add Song", null, panel, null);
				panel.setLayout(new MigLayout("", "[95.00][grow]", "[38.00][44.00][39.00][40.00][45.00][]"));
				{
					JLabel lblNewLabel = new JLabel("Enter Song Title:");
					panel.add(lblNewLabel, "cell 0 0,alignx left");
				}
				{
					txtSongTitle = new JTextField();
					panel.add(txtSongTitle, "cell 1 0,growx");
					txtSongTitle.setColumns(10);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Enter Artist Name:");
					panel.add(lblNewLabel_1, "cell 0 1,alignx left");
				}
				{
					txtArtistName = new JTextField();
					panel.add(txtArtistName, "cell 1 1,growx");
					txtArtistName.setColumns(10);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Enter Genre:");
					panel.add(lblNewLabel_2, "cell 0 2,alignx left");
				}
				{
					txtGenre = new JTextField();
					panel.add(txtGenre, "cell 1 2,growx");
					txtGenre.setColumns(10);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Enter Rating:");
					panel.add(lblNewLabel_3, "cell 0 3,alignx left");
				}
				{
					txtRating = new JTextField();
					panel.add(txtRating, "cell 1 3,growx");
					txtRating.setColumns(10);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Enter Time (in seconds):");
					panel.add(lblNewLabel_4, "cell 0 4,alignx left");
				}
				{
					txtTime = new JTextField();
					panel.add(txtTime, "cell 1 4,growx");
					txtTime.setColumns(10);
				}
				{
					JButton btnAddSong = new JButton("Add Song");
					btnAddSong.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try{
								// this is how to add a song
								int rating = Integer.parseInt(txtRating.getText());
								int time = Integer.parseInt(txtTime.getText());
								if(rating > 5){
									JOptionPane.showMessageDialog(contentPanel, "Rating cannot be greater than 5");
									return;
								}
								if(txtSongTitle.getText().trim().equals("") || txtArtistName.getText().equals("") || txtGenre.getText().trim().equals(""))
								{
									JOptionPane.showMessageDialog(contentPanel, "You have one or more empty textboxes!");
								}
								cd.addSong(new Song(txtSongTitle.getText(), txtArtistName.getText(), txtGenre.getText(), rating,new Time(time)));
							}
							catch(NumberFormatException e)
							{
								JOptionPane.showMessageDialog(contentPanel, "Error: Check your textboxes. You may have entered strings where you weren't supposed to, or left a box blank! ");
							}
						}
					});
					panel.add(btnAddSong, "cell 1 5,growx");
				}
			}
			{
				JPanel panel1 = new JPanel();
				tabbedPane.addTab("Remove Song", null, panel1, null);
				panel1.setLayout(new MigLayout("", "[grow]", "[][]"));
				{
					panel1.add(comboBoxSong2, "cell 0 0,growx");
				}
				{
					JButton btnNewButton = new JButton("Remove Song");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//this code removes a song
							Song songToRemove = (Song)comboBoxSong2.getSelectedItem();
							cd.removeSong(cd.getSongs().indexOf(songToRemove));
							comboBoxSong2.removeItem(songToRemove);
							int itemCount = comboBoxSong2.getItemCount();
							updateComboBoxWithList(comboBoxSong2);
						}
					});
					panel1.add(btnNewButton, "cell 0 1,growx");
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Sort Songs", null, panel, null);
				panel.setLayout(new MigLayout("", "[505.00,grow]", "[][][][]"));
				{
					JButton btnSortTitle = new JButton("Sort Songs By Title");
					btnSortTitle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cd.sortByTitle();
						}
					});
					panel.add(btnSortTitle, "cell 0 0,growx");
				}
				{
					JButton btnSortArtist = new JButton("Sort Songs By Artist");
					btnSortArtist.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cd.sortByArtist();
						}
					});
					panel.add(btnSortArtist, "cell 0 1,growx");
				}
				{
					JButton btnSortTime = new JButton("Sort Songs By Time");
					btnSortTime.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cd.sortByTime();
						}
					});
					panel.add(btnSortTime, "cell 0 2,growx");
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 0 1,grow");
			JButton button = new JButton("Return to main menu");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setVerticalAlignment(SwingConstants.BOTTOM);
			button.setMinimumSize(new Dimension(163, 43));
			button.setMaximumSize(new Dimension(163, 43));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGap(0, 489, Short.MAX_VALUE)
					.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(346, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
					);
			gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
					.addGap(0, 45, Short.MAX_VALUE)
					.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);
			panel.setLayout(gl_panel);
		}

	}

	/*
	 * Parameters: JComboBox<Song> comboBox which is the combobox to update
	 * Return Type: void
	 * Description: updates a combobox with the latest song info
	 */
	public void updateComboBoxWithList(JComboBox<Song> comboBox)
	{
		DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
		model.removeAllElements();
			for (int i = 0; i < cd.getSongs().size(); i++)
				comboBox.addItem(cd.getSongs().get(i));
	}

}
