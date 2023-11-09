package it602003.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

import it602003.objects.CategoryObject;
import it602003.objects.SectionObject;
import it602003.objects.UserObject;
import it602003.process.Category;
import it602003.process.Section;
import it602003.process.User;
import it602003.process.processImpl.CategoryImpl;
import it602003.process.processImpl.SectionImpl;
import it602003.process.processImpl.UserImpl;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class home {
	private Category cat;
	private Section sec;
	private User user;
	private ArrayList<CategoryObject> itemsArrayList;
	private ArrayList<SectionObject> sectArrayList;
	private ArrayList<UserObject> userArrayList;
	private JFrame frame;
	private JTable tblCategoryList;
	private JScrollPane scrollPane;
	private JPanel panelAdd, panelEdit;
	private JTextField txtSearch;
	static boolean searchMode;
	private String searchText;
	private JTextField txtName;
	private JTextField txtName_en;
	private JTextField txtName_en_edit;
	private JComboBox cbSectionSelect_edit;
	private JComboBox cbManagerSelect_edit;
	private JComboBox cbSectionSelect;
	private JComboBox cbAuthorSelect;
	private JComboBox cbManagerSelect;
	private JComboBox cbLanguageSelect;
	private JComboBox cbLanguageSelect_edit;
	private JTextArea txtNotes;
	private JTextPane txtName_Edit;
	private JTextPane txtNote_edit;
	private JLabel lblAuthorName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Category cat = new CategoryImpl();
		Section sec = new SectionImpl();
		User user = new UserImpl();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home(cat, sec, user);
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
	public home(Category cat, Section sec, User user) {
		this.cat = cat;
		this.sec = sec;
		this.user = user;
		this.itemsArrayList = cat.getCategoryObjects();
		sectArrayList = sec.getSectionObjects(null, (byte)20);
		userArrayList = user.getUserObjects(null,(byte) 20);
		this.searchMode = false;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(108, 126, 226));
		frame.setBounds(100, 100, 1194, 788);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblMainTitle = new JLabel("Nguyen Trong Tu Tam - 058 - Category");
		lblMainTitle.setBounds(0, 0, 1176, 40);
		lblMainTitle.setPreferredSize(new Dimension(134, 40));
		lblMainTitle.setMinimumSize(new Dimension(134, 20));
		lblMainTitle.setMaximumSize(new Dimension(134, 20));
		lblMainTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMainTitle.setOpaque(true);
		lblMainTitle.setForeground(Color.WHITE);
		lblMainTitle.setBackground(new Color(108, 126, 226));
		lblMainTitle.setFont(new Font("Cascadia Code", Font.BOLD, 23));
		lblMainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblMainTitle);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(null);
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(new Color(146, 185, 228));
		tabbedPane.setBounds(0, 44, 1176, 705);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelList = new JPanel();
		panelList.setAlignmentY(Component.TOP_ALIGNMENT);
		panelList.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelList.setBorder(null);
		panelList.setBackground(new Color(146, 185, 228));
		tabbedPane.addTab("List of Category", null, panelList, null);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setBackgroundAt(0, new Color(146, 185, 228));
		panelList.setLayout(null);
		
		tblCategoryList = new JTable() {
		  public boolean isCellEditable(int rowIndex, int colIndex) {
			  return false; //Disallow the editing of any cell
		  }
		};
		
		tblCategoryList.setBackground(new Color(215, 249, 250));
		tblCategoryList.setColumnSelectionAllowed(true);
		tblCategoryList.setUpdateSelectionOnSort(false);
		tblCategoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCategoryList.setBounds(10, 75, 967, 472);
		
		
		JLabel lblCategoryListTitle = new JLabel("CATEGORY LIST");
		lblCategoryListTitle.setOpaque(true);
		lblCategoryListTitle.setBackground(new Color(240, 255, 240));
		lblCategoryListTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryListTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCategoryListTitle.setBounds(458, 11, 235, 36);
		panelList.add(lblCategoryListTitle);
		
		JButton btnEdit = new JButton("Edit");
		
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBounds(1062, 246, 99, 29);
		panelList.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(1062, 302, 99, 29);
		panelList.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("View Full Cell");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(179, 224, 158));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 630, 112, 36);
		panelList.add(lblNewLabel);
		
		JLabel lblViewFullCell = new JLabel("");
		lblViewFullCell.setForeground(Color.BLACK);
		lblViewFullCell.setHorizontalTextPosition(SwingConstants.LEFT);
		lblViewFullCell.setOpaque(true);
		lblViewFullCell.setHorizontalAlignment(SwingConstants.LEFT);
		lblViewFullCell.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblViewFullCell.setBackground(new Color(250, 181, 173));
		lblViewFullCell.setBounds(132, 615, 900, 62);
		panelList.add(lblViewFullCell);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 240, 240));
		scrollPane.setBounds(10, 118, 1022, 486);
		scrollPane.setViewportView(tblCategoryList);
		
		panelList.add(scrollPane);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 78, 328, 29);
		panelList.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.setBounds(348, 78, 88, 29);
		panelList.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(1062, 357, 99, 29);
		panelList.add(btnAdd);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQuit.setBounds(1062, 412, 99, 29);
		panelList.add(btnQuit);
		
		panelEdit = new JPanel();
		panelEdit.setBackground(new Color(146, 185, 227));
		panelEdit.setBorder(null);
		
		tabbedPane.addTab("Edit category", null, panelEdit, null);
		tabbedPane.setBackgroundAt(1, new Color(146, 185, 227));
		panelEdit.setLayout(null);
		
		JLabel lblEditTitle = new JLabel("EDIT CATEGORY");
		lblEditTitle.setBounds(458, 11, 235, 36);
		lblEditTitle.setOpaque(true);
		lblEditTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditTitle.setBackground(new Color(240, 255, 240));
		panelEdit.add(lblEditTitle);
		
		JButton btnCancel_Edit = new JButton("Cancel");
		btnCancel_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(panelEdit);
				tabbedPane.setSelectedComponent(panelList);
			}
		});
		btnCancel_Edit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel_Edit.setBorder(null);
		btnCancel_Edit.setBackground(new Color(255, 255, 128));
		btnCancel_Edit.setBounds(411, 622, 99, 29);
		panelEdit.add(btnCancel_Edit);
		
		JButton btnSave = new JButton("Save");
		
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(547, 622, 99, 29);
		panelEdit.add(btnSave);
		
		JPanel panelChildAdd_1 = new JPanel();
		panelChildAdd_1.setLayout(null);
		panelChildAdd_1.setBackground(new Color(215, 249, 250));
		panelChildAdd_1.setBounds(10, 78, 1151, 512);
		panelEdit.add(panelChildAdd_1);
		
		JLabel lblAddName_1 = new JLabel("Name");
		lblAddName_1.setOpaque(true);
		lblAddName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddName_1.setBackground(new Color(164, 195, 162));
		lblAddName_1.setBounds(48, 25, 61, 25);
		panelChildAdd_1.add(lblAddName_1);
		
		JLabel lblAddSection_1 = new JLabel("Section");
		lblAddSection_1.setOpaque(true);
		lblAddSection_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSection_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddSection_1.setBackground(new Color(164, 195, 162));
		lblAddSection_1.setBounds(48, 74, 112, 25);
		panelChildAdd_1.add(lblAddSection_1);
		
		JLabel lblAddNotes_1 = new JLabel("Notes");
		lblAddNotes_1.setOpaque(true);
		lblAddNotes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNotes_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddNotes_1.setBackground(new Color(164, 195, 162));
		lblAddNotes_1.setBounds(48, 121, 61, 25);
		panelChildAdd_1.add(lblAddNotes_1);
		
		cbSectionSelect_edit = new JComboBox();
		cbSectionSelect_edit.setBounds(171, 73, 204, 25);
		panelChildAdd_1.add(cbSectionSelect_edit);
		
		JLabel lblAddAuthor_1 = new JLabel("Author");
		lblAddAuthor_1.setOpaque(true);
		lblAddAuthor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAuthor_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddAuthor_1.setBackground(new Color(164, 195, 162));
		lblAddAuthor_1.setBounds(48, 252, 112, 25);
		panelChildAdd_1.add(lblAddAuthor_1);
		
		JLabel lblAddManager_1 = new JLabel("Manager");
		lblAddManager_1.setOpaque(true);
		lblAddManager_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddManager_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddManager_1.setBackground(new Color(164, 195, 162));
		lblAddManager_1.setBounds(48, 312, 112, 25);
		panelChildAdd_1.add(lblAddManager_1);
		
		cbManagerSelect_edit = new JComboBox();
		cbManagerSelect_edit.setBounds(171, 315, 204, 25);
		panelChildAdd_1.add(cbManagerSelect_edit);
		
		JLabel lblEnglishName_1 = new JLabel("English name");
		lblEnglishName_1.setOpaque(true);
		lblEnglishName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnglishName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnglishName_1.setBackground(new Color(164, 195, 162));
		lblEnglishName_1.setBounds(48, 374, 112, 25);
		panelChildAdd_1.add(lblEnglishName_1);
		
		txtName_en_edit = new JTextField();
		txtName_en_edit.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName_en_edit.setColumns(10);
		txtName_en_edit.setBounds(191, 374, 237, 25);
		panelChildAdd_1.add(txtName_en_edit);
		
		JLabel lblAddLanguage_1 = new JLabel("Language");
		lblAddLanguage_1.setOpaque(true);
		lblAddLanguage_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLanguage_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddLanguage_1.setBackground(new Color(164, 195, 162));
		lblAddLanguage_1.setBounds(48, 423, 112, 25);
		panelChildAdd_1.add(lblAddLanguage_1);
		
		cbLanguageSelect_edit = new JComboBox();
		cbLanguageSelect_edit.setBounds(191, 426, 204, 25);
		panelChildAdd_1.add(cbLanguageSelect_edit);
		
		txtName_Edit = new JTextPane();
		txtName_Edit.setContentType("text/html");
		txtName_Edit.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName_Edit.setBounds(119, 25, 237, 25);
		panelChildAdd_1.add(txtName_Edit);
		
		txtNote_edit = new JTextPane();
		txtNote_edit.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNote_edit.setContentType("text/html");
		txtNote_edit.setBounds(119, 121, 440, 94);
		panelChildAdd_1.add(txtNote_edit);
		
		lblAuthorName = new JLabel("");
		lblAuthorName.setBackground(Color.WHITE);
		lblAuthorName.setOpaque(true);
		lblAuthorName.setBounds(170, 252, 270, 25);
		panelChildAdd_1.add(lblAuthorName);
		
		panelAdd = new JPanel();
		panelAdd.setBackground(new Color(146, 185, 227));
		panelAdd.setBorder(null);
		panelAdd.setForeground(Color.BLACK);
		tabbedPane.addTab("Add new category", null, panelAdd, null);
		panelAdd.setLayout(null);
		
		JLabel lblAddTitle = new JLabel("ADD CATEGORY");
		lblAddTitle.setOpaque(true);
		lblAddTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddTitle.setBackground(new Color(240, 255, 240));
		lblAddTitle.setBounds(458, 11, 235, 36);
		panelAdd.add(lblAddTitle);
		
		JPanel panelChildAdd = new JPanel();
		panelChildAdd.setBackground(new Color(215, 249, 250));
		panelChildAdd.setBounds(10, 78, 1151, 512);
		panelAdd.add(panelChildAdd);
		panelChildAdd.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setBounds(138, 25, 237, 25);
		txtName.setColumns(10);
		panelChildAdd.add(txtName);
		
		JLabel lblAddName = new JLabel("Name");
		lblAddName.setBackground(new Color(164, 195, 162));
		lblAddName.setBounds(48, 25, 61, 25);
		lblAddName.setOpaque(true);
		lblAddName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddName.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelChildAdd.add(lblAddName);
		
		JLabel lblAddSection = new JLabel("Section");
		lblAddSection.setOpaque(true);
		lblAddSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSection.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddSection.setBackground(new Color(164, 195, 162));
		lblAddSection.setBounds(48, 74, 112, 25);
		panelChildAdd.add(lblAddSection);
		
		JLabel lblAddNotes = new JLabel("Notes");
		lblAddNotes.setOpaque(true);
		lblAddNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNotes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddNotes.setBackground(new Color(164, 195, 162));
		lblAddNotes.setBounds(48, 121, 61, 25);
		panelChildAdd.add(lblAddNotes);
		
		txtNotes = new JTextArea();
		txtNotes.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNotes.setBounds(119, 123, 309, 94);
		panelChildAdd.add(txtNotes);
		
		cbSectionSelect = new JComboBox();
		cbSectionSelect.setBounds(171, 73, 204, 25);
		panelChildAdd.add(cbSectionSelect);
		
		JLabel lblAddAuthor = new JLabel("Author");
		lblAddAuthor.setOpaque(true);
		lblAddAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAuthor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddAuthor.setBackground(new Color(164, 195, 162));
		lblAddAuthor.setBounds(48, 252, 112, 25);
		panelChildAdd.add(lblAddAuthor);
		
		cbAuthorSelect = new JComboBox();
		cbAuthorSelect.setBounds(171, 255, 204, 25);
		panelChildAdd.add(cbAuthorSelect);
		
		JLabel lblAddManager = new JLabel("Manager");
		lblAddManager.setOpaque(true);
		lblAddManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddManager.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddManager.setBackground(new Color(164, 195, 162));
		lblAddManager.setBounds(48, 312, 112, 25);
		panelChildAdd.add(lblAddManager);
		
		cbManagerSelect = new JComboBox();
		cbManagerSelect.setBounds(171, 315, 204, 25);
		panelChildAdd.add(cbManagerSelect);
		
		JLabel lblEnglishName = new JLabel("English name");
		lblEnglishName.setOpaque(true);
		lblEnglishName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnglishName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnglishName.setBackground(new Color(164, 195, 162));
		lblEnglishName.setBounds(48, 374, 112, 25);
		panelChildAdd.add(lblEnglishName);
		
		txtName_en = new JTextField();
		txtName_en.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName_en.setColumns(10);
		txtName_en.setBounds(191, 374, 237, 25);
		panelChildAdd.add(txtName_en);
		
		JLabel lblAddLanguage = new JLabel("Language");
		lblAddLanguage.setOpaque(true);
		lblAddLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLanguage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddLanguage.setBackground(new Color(164, 195, 162));
		lblAddLanguage.setBounds(48, 423, 112, 25);
		panelChildAdd.add(lblAddLanguage);
		
		cbLanguageSelect = new JComboBox();
		cbLanguageSelect.setBounds(191, 426, 204, 25);
		panelChildAdd.add(cbLanguageSelect);
		
		JButton btnCancel_Add = new JButton("Cancel");
		btnCancel_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(panelAdd);
				tabbedPane.setSelectedComponent(panelList);
				
			}
		});
		btnCancel_Add.setBorder(null);
		btnCancel_Add.setBackground(new Color(255, 255, 128));
		btnCancel_Add.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel_Add.setBounds(411, 622, 99, 29);
		panelAdd.add(btnCancel_Add);
		
		JButton btnAddNew = new JButton("Add");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				int sectionId = sectArrayList.get(cbSectionSelect.getSelectedIndex()).getSection_id();
				String notes = txtNotes.getText();
				
				Calendar c = Calendar.getInstance();
				String createdDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
				
				int authorId = userArrayList.get(cbAuthorSelect.getSelectedIndex()).getUser_id();
				String lastModified = "";
				int managerId = userArrayList.get(cbManagerSelect.getSelectedIndex()).getUser_id();
				boolean enable = true;
				boolean deleted = false;
				String images = "";
				String name_en = txtName_en.getText();
				int language = cbLanguageSelect.getSelectedIndex();
				
				CategoryObject cate = new CategoryObject(name,(short) sectionId, notes, createdDate, authorId, 
						lastModified, managerId, enable, deleted, images, name_en,(byte) language);
				if(cat.addCategory(cate)) {
					JOptionPane.showMessageDialog(null, "Add new category successfully!!!");
				}
				else JOptionPane.showMessageDialog(null, "Can not add new category!!!");
				clearForm();
				itemsArrayList = cat.getCategoryObjects();
				loadTable(itemsArrayList);
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddNew.setBounds(547, 622, 99, 29);
		panelAdd.add(btnAddNew);
		//đưa dữ liệu vào bảng
		loadTable(this.itemsArrayList);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMode = true;
				searchText = txtSearch.getText();
				itemsArrayList = cat.getCategoryObjectByName(searchText);
				loadTable(itemsArrayList);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblCategoryList.getSelectedColumn() != -1) {
//					tabbedPane.add(panelEdit, 1);
//					tabbedPane.setTitleAt(1, "Edit category");
					tabbedPane.setSelectedComponent(panelEdit);
					updateData();
					int id = -1;
					String temp = tblCategoryList.getValueAt(tblCategoryList.getSelectedRow(), 0).toString();
					id = Integer.valueOf(temp);
					
					CategoryObject catObj = cat.getCategoryObjectById(id);
					fillUpdateForm(catObj);
					updateUSelection(catObj);;
				}
				else {
					JOptionPane.showMessageDialog(null, "Select row first!!!");
				}	
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = -1;
				String temp = tblCategoryList.getValueAt(tblCategoryList.getSelectedRow(), 0).toString();
				id = Integer.valueOf(temp);
				CategoryObject catObj = cat.getCategoryObjectById(id);
				
				String name = "";
				try {
					name = txtName_Edit.getDocument().getText(0,txtName_Edit.getDocument().getLength());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catObj.setCategory_name(name);
				
				int sectionId = sectArrayList.get(cbSectionSelect_edit.getSelectedIndex()).getSection_id();
				catObj.setCategory_section_id((short)sectionId);
				
				String notes = "";
				try {
					notes = txtNote_edit.getDocument().getText(0,txtNote_edit.getDocument().getLength());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catObj.setCategory_notes(notes);
				
				Calendar c = Calendar.getInstance();
				String lastModified = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
				catObj.setCategory_last_modified(lastModified);

				int managerId = userArrayList.get(cbManagerSelect_edit.getSelectedIndex()).getUser_id();
				catObj.setCategory_manager_id(managerId);
				
				String name_en = txtName_en_edit.getText();
				catObj.setCategory_name_en(name_en);
				
				int language = cbLanguageSelect_edit.getSelectedIndex();
				catObj.setCategory_language((byte)language);
				System.err.println(catObj);
				
				if(cat.updateCategoryObject(catObj)) {
					JOptionPane.showMessageDialog(null, "Updated successfully!!!");
				}

				itemsArrayList = cat.getCategoryObjects();
				loadTable(itemsArrayList);
				
				tabbedPane.remove(panelEdit);
				tabbedPane.setSelectedComponent(panelList);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblCategoryList.getSelectedColumn() != -1) {
					Object[] options = {"Yes", "No"};
					int id = -1;
					String categoryName = tblCategoryList.getValueAt(tblCategoryList.getSelectedRow(), 1).toString();
					String temp = tblCategoryList.getValueAt(tblCategoryList.getSelectedRow(), 0).toString();
					id = Integer.valueOf(temp);
					int n = JOptionPane.showOptionDialog(frame, "<html>Category '" + categoryName +"' Id "+ temp +" will be deleted!!!</html>", 
							"Comfirm delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (n==0) {
						if (id == -1) {
							JOptionPane.showMessageDialog(null, "Category not exist!!!");
						}
						deleteCategory(id);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Select row first!!!");
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				tabbedPane.add(panelAdd);
//				tabbedPane.setTitleAt(2, "Add new category");
				tabbedPane.setSelectedComponent(panelAdd);
				updateData();
				updateAddSelection();
				
			}
		});
		
		tblCategoryList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cellValue = "";
				try {
					cellValue =	tblCategoryList.getValueAt
							(tblCategoryList.getSelectedRow(), tblCategoryList.getSelectedColumn()).toString();
				}
				catch(NullPointerException ne) {
				}
				
				if (cellValue != null)
					lblViewFullCell.setText("<html> <div style=\\\"width:500px;\\\">" + cellValue + "</div></html>");
				else lblViewFullCell.setText("");
			}
		});
	}
	public void loadTable(ArrayList<CategoryObject> itemsArrayList) {
		String[] columnNames = {"ID","Name", "SectionId", "Notes", "CreatedDate","AuthorId", "LastModified",
				"ManagerId", "Image", "EnglishName", "Language"};
		
		DefaultTableModel tableData = new DefaultTableModel();
		tableData.setColumnIdentifiers(columnNames);
		//Chuyển đổi category object sang object
		for(int i = 0; i < itemsArrayList.size(); i++) {
			Object[] obj = new Object[] {
					itemsArrayList.get(i).getCategory_id(),
					itemsArrayList.get(i).getCategory_name(),
					itemsArrayList.get(i).getCategory_section_id(),
					itemsArrayList.get(i).getCategory_notes(),
					itemsArrayList.get(i).getCategory_created_date(),
					itemsArrayList.get(i).getCategory_created_author_id(),
					itemsArrayList.get(i).getCategory_last_modified(),
					itemsArrayList.get(i).getCategory_manager_id(),
					itemsArrayList.get(i).getCategory_image(),
					itemsArrayList.get(i).getCategory_name_en(),
					itemsArrayList.get(i).getCategory_language()
			};
			tableData.addRow(obj);
		}
		tblCategoryList.setModel(tableData);
	}
	private void updateData() {
		this.itemsArrayList = cat.getCategoryObjects();
		this.sectArrayList = sec.getSectionObjects(null,(byte) 50);
		this.userArrayList = user.getUserObjects(null, (byte)50);
	}
	private void deleteCategory(int id) {
		if(!cat.deleteCategory(id)) {
			JOptionPane.showMessageDialog(null, "Can not delete category!!!");
			return;
		}
		this.itemsArrayList = cat.getCategoryObjects();
		loadTable(this.itemsArrayList);
	}
	private void clearForm() {
		txtName.setText("");
		txtNotes.setText("");
		txtName_en.setText("");
		if (cbAuthorSelect.getComponentCount() > 0) cbAuthorSelect.setSelectedIndex(0);
		if (cbManagerSelect.getComponentCount() > 0) cbManagerSelect.setSelectedIndex(0);
		cbLanguageSelect.setSelectedIndex(0);
	}
	private void fillUpdateForm(CategoryObject catObj) {
		txtName_Edit.setText("<html>" + catObj.getCategory_name() + "</html>");
		txtNote_edit.setText("<html>" + catObj.getCategory_notes() + "</html>");
		txtName_en_edit.setText(catObj.getCategory_name_en());
		UserObject u = user.getUserObject(catObj.getCategory_created_author_id());
		lblAuthorName.setText(u.getUser_fullname());
		
	}
	private void updateAddSelection() {
		DefaultComboBoxModel sectionData = new DefaultComboBoxModel();
		for (SectionObject sec : sectArrayList) {
			sectionData.addElement("<html>" + sec.getSection_name() + "</html>");
		}
		cbSectionSelect.setModel(sectionData);
		cbSectionSelect_edit.setModel(sectionData);
		if(sectArrayList.size() > 0) {
			cbSectionSelect.setSelectedIndex(0);
			cbSectionSelect_edit.setSelectedIndex(0);
		}
		
		
		DefaultComboBoxModel authorData = new DefaultComboBoxModel();
		for (UserObject u : userArrayList) {
			authorData.addElement("<html>" + u.getUser_fullname() + "</html>");
		}
		
		DefaultComboBoxModel managerData = new DefaultComboBoxModel();
		for (UserObject u : userArrayList) {
			managerData.addElement("<html>" + u.getUser_fullname() + "</html>");
		}
		
		cbAuthorSelect.setModel(authorData);
		cbManagerSelect.setModel(managerData);
		cbManagerSelect_edit.setModel(managerData);
		
		if (userArrayList.size() > 0) {
			cbAuthorSelect.setSelectedIndex(0);
			cbManagerSelect.setSelectedIndex(0);
			cbManagerSelect_edit.setSelectedIndex(0);
		}
		
		DefaultComboBoxModel languageData = new DefaultComboBoxModel();
		languageData.addElement("Vietnamese");
		languageData.addElement("English");
		languageData.addElement("Thailand");
		languageData.addElement("Rusia");
		languageData.addElement("Korean");
		languageData.addElement("Japanese");
		
		cbLanguageSelect.setModel(languageData);
		cbLanguageSelect_edit.setModel(languageData);
		cbLanguageSelect.setSelectedIndex(0);
		cbLanguageSelect_edit.setSelectedIndex(0);
	}
	private void updateUSelection(CategoryObject catObj) {
		DefaultComboBoxModel sectionData = new DefaultComboBoxModel();
		int i = 0;
		int d = 0;
		for (SectionObject sec : sectArrayList) {
			if (catObj.getCategory_section_id() == sec.getSection_id()) {
				d = i;
			}
			sectionData.addElement("<html>" + sec.getSection_name() + "</html>");
			 i++;
		}
		cbSectionSelect_edit.setModel(sectionData);
		if(sectArrayList.size() > 0) {
			cbSectionSelect_edit.setSelectedIndex(d);
		}
		
		i = 0;
		d=0;
		DefaultComboBoxModel managerData = new DefaultComboBoxModel();
		for (UserObject u : this.userArrayList) {
			if (catObj.getCategory_manager_id() == u.getUser_id()) {
				d = i;
				System.err.println(catObj.getCategory_manager_id());
				System.out.println(u.getUser_id());
			}
			managerData.addElement("<html>" + u.getUser_fullname() + "</html>");
			i++;
		}
		cbManagerSelect_edit.setModel(managerData);
		cbManagerSelect_edit.setSelectedIndex(d);
		
		DefaultComboBoxModel languageData = new DefaultComboBoxModel();
		languageData.addElement("Vietnamese");
		languageData.addElement("English");
		languageData.addElement("Thailand");
		languageData.addElement("Rusia");
		languageData.addElement("Korean");
		languageData.addElement("Japanese");
		
		
		cbLanguageSelect_edit.setModel(languageData);
		cbLanguageSelect_edit.setSelectedIndex(catObj.getCategory_language());
	}
}
