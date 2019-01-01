

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: This class deals with all code relating to the submenu1
 */
public class Submenu1 extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JPanel buttonPane;
	private JTextField txtInputFile;
	private JTextArea txtCDList;
	private JTextArea txtCDInfo;
	private JTextField txtStartIndex;
	private JTextField txtEndIndex;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	/*
	 * Parameters: none
	 * Return Type: none
	 * Description: Constructor for the first submenu, and this also includes action listeners and creates all components
	 */
	public Submenu1() {
		setModal(true);
		setBounds(100, 100, 570, 491);
		{
			buttonPane = new JPanel();
			{
				btnNewButton = new JButton("Return to main menu");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();	//this is how we exit
					}
				});
				btnNewButton.setMinimumSize(new Dimension(163, 43));
				btnNewButton.setMaximumSize(new Dimension(163, 43));
				btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
							.addContainerGap(346, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
					);
			gl_buttonPane.setVerticalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);
			buttonPane.setLayout(gl_buttonPane);
		}
		getContentPane().setLayout(new MigLayout("", "[514px,grow]", "[370px,grow][45px]"));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JTextArea textArea = new JTextArea();
		JTextArea songListArea = new JTextArea();
		JComboBox<CD> listComboBox1 = new JComboBox<CD>();
		JComboBox<CD> listComboBox2 = new JComboBox<CD>();
		JComboBox<CD> listComboBox3 = new JComboBox<CD>();
		JComboBox<CD> listComboBox4 = new JComboBox<CD>();
		JComboBox<CD> listComboBox5 = new JComboBox<CD>();
		JComboBox<CD> listComboBox6 = new JComboBox<CD>();
		listComboBox6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					CD selectedCD = (CD)listComboBox6.getSelectedItem();
					int counter = 0;
					songListArea.setText("");
					for(Song sg : selectedCD.getSongs())
					{
						songListArea.append(counter + ": " + sg.getTitle() + "\n");
						counter++;
					}
				}
			}
		});
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {

				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if(sourceTabbedPane.getTitleAt(index).equals("Display particular CD Info")){
					updateComboBoxWithList(listComboBox1);
				}

				else if(sourceTabbedPane.getTitleAt(index).equals("Remove CD"))
				{
					updateComboBoxWithList(listComboBox2);
				}
				else if(sourceTabbedPane.getTitleAt(index).equals("Copy CD"))
				{
					updateComboBoxWithList(listComboBox3);
				}
				else if(sourceTabbedPane.getTitleAt(index).equals("List Songs in Common between two CDs"))
				{
					updateComboBoxWithList(listComboBox4);
					updateComboBoxWithList(listComboBox5);
				}
				else if(sourceTabbedPane.getTitleAt(index).equals("Create a Sub-CD"))
				{
					updateComboBoxWithList(listComboBox6);
				}

			}
		});
		getContentPane().add(tabbedPane, "cell 0 0,grow");

		JPanel displayCDPanel = new JPanel();
		tabbedPane.addTab("Display CD List", null, displayCDPanel, null);
		displayCDPanel.setLayout(new MigLayout("", "[147px,grow]", "[grow][baseline]"));

		txtCDList = new JTextArea();
		txtCDList.setEditable(false);
		displayCDPanel.add(txtCDList, "cell 0 0,grow");

		JButton btnDisplayUpdatedCd = new JButton("Display Updated CD List");
		btnDisplayUpdatedCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayAddedCDs();
			}
		});
		displayCDPanel.add(btnDisplayUpdatedCd, "cell 0 1,growx,aligny top");

		JPanel displayParticularCDPanel = new JPanel();

		tabbedPane.addTab("Display particular CD Info", null, displayParticularCDPanel, null);
		displayParticularCDPanel.setLayout(new MigLayout("", "[178.00][538.00,grow]", "[266.00,grow][26.00][]"));

		txtCDInfo = new JTextArea();
		displayParticularCDPanel.add(txtCDInfo, "cell 0 0 2 1,grow");

		JLabel lblNewLabel_1 = new JLabel("Select CD:");
		displayParticularCDPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");





		displayParticularCDPanel.add(listComboBox1, "cell 1 1,growx");

		JButton btnNewButton_1 = new JButton("Display CD Info");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//displays info on CD selected
				System.out.println("");
				displayParticularCDInfo((CD)listComboBox1.getSelectedItem());
			}
		});
		displayParticularCDPanel.add(btnNewButton_1, "cell 0 2 2 1,grow");

		JPanel addCDPanel = new JPanel();
		tabbedPane.addTab("Add CD", null, addCDPanel, null);
		addCDPanel.setLayout(new MigLayout("", "[][grow,center]", "[][center][]"));

		JLabel lblEnterInputFile = new JLabel("Enter Input File:");
		addCDPanel.add(lblEnterInputFile, "cell 0 0");

		txtInputFile = new JTextField();
		addCDPanel.add(txtInputFile, "cell 1 0,growx");
		txtInputFile.setColumns(10);

		JButton btnAddCd = new JButton("Add CD");
		btnAddCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adds CD
				Driver.inputCDInfo(txtInputFile.getText());
			}
		});
		addCDPanel.add(btnAddCd, "cell 1 1,grow");

		JPanel removeCDPanel = new JPanel();
		tabbedPane.addTab("Remove CD", null, removeCDPanel, null);
		removeCDPanel.setLayout(new MigLayout("", "[142.00][456.00,grow]", "[23.00][]"));

		JLabel lblNewLabel = new JLabel("Select CD To Delete:");
		removeCDPanel.add(lblNewLabel, "cell 0 0,alignx trailing");

		JButton btnDeleteCd = new JButton("Remove CD");
		btnDeleteCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//removes CD by getting cd from combobox
				CD cdToRemove = (CD)listComboBox2.getSelectedItem();
				Driver.removeCD(cdToRemove);
				listComboBox2.removeItem(cdToRemove);
				int itemCount = listComboBox2.getItemCount();
			}

		});

		removeCDPanel.add(listComboBox2, "cell 1 0,growx");
		removeCDPanel.add(btnDeleteCd, "cell 1 1,grow");

		JPanel copyCDPanel = new JPanel();
		tabbedPane.addTab("Copy CD", null, copyCDPanel, null);
		copyCDPanel.setLayout(new MigLayout("", "[85.00][grow]", "[][]"));

		JLabel lblSelectCdTo = new JLabel("Select CD To Copy");
		copyCDPanel.add(lblSelectCdTo, "cell 0 0,alignx trailing");

		copyCDPanel.add(listComboBox3, "cell 1 0,growx");

		JButton btnCopyCd = new JButton("Copy CD");
		btnCopyCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//copy's CD from listBox
				CD itemToCopy = (CD)listComboBox3.getSelectedItem();
				CD copyCD = new CD(itemToCopy);
				listComboBox3.addItem(copyCD);
				Driver.addCD(copyCD);
				copyCD.setTitle("copy" + copyCD.getTitle());
			}
		});

		copyCDPanel.add(btnCopyCd, "cell 1 1,grow");

		JPanel subCDPanel = new JPanel();
		tabbedPane.addTab("Create a Sub-CD", null, subCDPanel, null);
		subCDPanel.setLayout(new MigLayout("", "[98.00][grow]", "[][][][][grow]"));

		JLabel lblSelectCd = new JLabel("Select CD:");
		subCDPanel.add(lblSelectCd, "cell 0 0,alignx left");

		subCDPanel.add(listComboBox6, "cell 1 0,growx");

		JLabel lblStartIndex = new JLabel("Start Index:");
		subCDPanel.add(lblStartIndex, "cell 0 1");

		txtStartIndex = new JTextField();
		subCDPanel.add(txtStartIndex, "cell 1 1,growx");
		txtStartIndex.setColumns(10);

		JLabel lblEndIndex = new JLabel("End Index:");
		subCDPanel.add(lblEndIndex, "cell 0 2");

		txtEndIndex = new JTextField();
		subCDPanel.add(txtEndIndex, "cell 1 2,growx");
		txtEndIndex.setColumns(10);

		JButton btnCreateSubcd = new JButton("Create Sub-CD");
		btnCreateSubcd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//this code creates a sub CD from the listComboBox6 component
				CD cdToUse = (CD)listComboBox6.getSelectedItem();
				try
				{
					int startIndex = Integer.parseInt(txtStartIndex.getText());
					if(startIndex < 0 || startIndex > cdToUse.getSongs().size()){
						JOptionPane.showMessageDialog(buttonPane, "Start Index cannot be below 0, or greater than size of array");
						return;
					}
					int endIndex = Integer.parseInt(txtEndIndex.getText());
					if(startIndex < 0 || startIndex > cdToUse.getSongs().size()){
						JOptionPane.showMessageDialog(buttonPane, "Start Index cannot be below 0, or greater than size of array");
						return;
					}
					if(startIndex > endIndex){
						JOptionPane.showMessageDialog(buttonPane, "Start Index cannot be greater than End Index");
						return;
					}
					Driver.createSubCD(cdToUse, startIndex, endIndex);
					updateComboBoxWithList(listComboBox6);
				}
				catch(NumberFormatException e)
				{
					
				}

			}
		});
		subCDPanel.add(btnCreateSubcd, "cell 1 3,grow");

		JLabel lblListOfSongs = new JLabel("List Of Songs\r\n(With Index Number)");
		subCDPanel.add(lblListOfSongs, "cell 0 4");


		subCDPanel.add(songListArea, "cell 1 4,grow");

		JPanel songsInCommonPanel = new JPanel();
		tabbedPane.addTab("List Songs in Common between two CDs", null, songsInCommonPanel, null);
		songsInCommonPanel.setLayout(new MigLayout("", "[113.00][grow]", "[][][][][grow]"));

		JLabel lblNewLabel_2 = new JLabel("Select First CD To Analyze:");
		songsInCommonPanel.add(lblNewLabel_2, "cell 0 0,alignx left");


		songsInCommonPanel.add(listComboBox4, "cell 1 0,growx");

		JLabel lblNewLabel_3 = new JLabel("Select Second CD To Analyze");
		songsInCommonPanel.add(lblNewLabel_3, "cell 0 1,alignx trailing");

		songsInCommonPanel.add(listComboBox5, "cell 1 1,growx");

		JButton btnCompareCDSongs = new JButton("Compare CD's");
		btnCompareCDSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//gets songs in common between two cd's and prints them
				CD firstCD = (CD)listComboBox4.getSelectedItem();
				ArrayList<Song> songsInCommon = firstCD.compareSongs((CD)listComboBox5.getSelectedItem());
				textArea.setText("");
				if(songsInCommon.size() == 0)
					textArea.append("No songs in common");
				for(Song sg: songsInCommon)
					textArea.append(sg.getTitle() + "\n");
			}
		});
		songsInCommonPanel.add(btnCompareCDSongs, "cell 1 2,grow");

		JLabel lblNewLabel_4 = new JLabel("Songs In Common");
		songsInCommonPanel.add(lblNewLabel_4, "cell 0 3 2 1,alignx center");

		songsInCommonPanel.add(textArea, "cell 0 4 2 1,grow");
		getContentPane().add(buttonPane, "cell 0 1,alignx center,growy");
	}
	/*
	 * Parameters: CD cd, which is used to get information to display
	 * Return Type: void
	 * Description: displays CD info in the text box
	 */
	public void displayParticularCDInfo(CD cd)
	{
		txtCDInfo.setText("");

		txtCDInfo.append("CD Title: " + cd.getTitle() + "\n");
		txtCDInfo.append("CD Length: " + cd.getTime() + "\n");
		txtCDInfo.append("Number of Songs: " + cd.getNumOfSongs());
	}
	/*
	 * Parameters: JComboBox<Song> comboBox which is the combobox to update
	 * Return Type: void
	 * Description: updates a combobox with the latest cd list
	 */
	public void updateComboBoxWithList(JComboBox<CD> comboBox)
	{
		int itemCount = comboBox.getItemCount();
		for(int i=0;i<itemCount;i++)
			comboBox.removeItemAt(0);
		for (int i = 0; i < Driver.getCDArray().size(); i++)
			comboBox.addItem(Driver.getCDArray().get(i));
	}
	public void displayAddedCDs()
	{
		txtCDList.setText("");
		for (CD cd: Driver.getCDArray())
		{
			txtCDList.append(cd.toString() + "\n");
		}
	}
}
