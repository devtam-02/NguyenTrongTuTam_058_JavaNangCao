package it602003.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

import it602003.objects.CategoryObject;
import it602003.objects.SectionObject;
import it602003.process.Category;
import it602003.process.processImpl.CategoryImpl;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

public class home {
	private Category cat;
	ArrayList<CategoryObject> itemsArrayList;
	private JFrame frame;
	private JTable tblCategoryList;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Category cat = new CategoryImpl();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home(cat);
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
	public home(Category cat) {
		this.cat = cat;
		this.itemsArrayList = cat.getCategoryObjects();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(108, 126, 226));
		frame.setBounds(100, 100, 1192, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNguyenTrongTu = new JLabel("Nguyen Trong Tu Tam - 058 - Category");
		lblNguyenTrongTu.setBounds(0, 0, 1176, 40);
		lblNguyenTrongTu.setPreferredSize(new Dimension(134, 40));
		lblNguyenTrongTu.setMinimumSize(new Dimension(134, 20));
		lblNguyenTrongTu.setMaximumSize(new Dimension(134, 20));
		lblNguyenTrongTu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNguyenTrongTu.setOpaque(true);
		lblNguyenTrongTu.setForeground(Color.WHITE);
		lblNguyenTrongTu.setBackground(new Color(108, 126, 226));
		lblNguyenTrongTu.setFont(new Font("Cascadia Code", Font.BOLD, 23));
		lblNguyenTrongTu.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNguyenTrongTu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(null);
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(new Color(146, 185, 228));
		tabbedPane.setBounds(0, 44, 1176, 637);
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
		
		
		JLabel lblCategoryList = new JLabel("CATEGORY LIST");
		lblCategoryList.setOpaque(true);
		lblCategoryList.setBackground(new Color(240, 255, 240));
		lblCategoryList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryList.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCategoryList.setBounds(458, 11, 235, 36);
		panelList.add(lblCategoryList);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBounds(1042, 245, 99, 29);
		panelList.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				String temp = "";
				temp = tblCategoryList.getValueAt(tblCategoryList.getSelectedRow(), 0).toString();
				id = Integer.valueOf(temp);
				deleteCategory(id);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(1042, 301, 99, 29);
		panelList.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("View Full Cell");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(179, 224, 158));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 558, 112, 36);
		panelList.add(lblNewLabel);
		
		JLabel lblViewFullCell = new JLabel("");
		lblViewFullCell.setForeground(Color.BLACK);
		lblViewFullCell.setHorizontalTextPosition(SwingConstants.LEFT);
		lblViewFullCell.setOpaque(true);
		lblViewFullCell.setHorizontalAlignment(SwingConstants.LEFT);
		lblViewFullCell.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblViewFullCell.setBackground(new Color(250, 181, 173));
		lblViewFullCell.setBounds(132, 547, 900, 62);
		panelList.add(lblViewFullCell);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 240, 240));
		scrollPane.setBounds(10, 75, 1022, 472);
		scrollPane.setViewportView(tblCategoryList);
		
		panelList.add(scrollPane);
		
		JPanel panelEdit = new JPanel();
		panelEdit.setBorder(null);
		tabbedPane.addTab("New tab", null, panelEdit, null);
		tabbedPane.setForegroundAt(1, Color.BLACK);
		
		JPanel panelAdd = new JPanel();
		panelAdd.setBorder(null);
		tabbedPane.addTab("New tab", null, panelAdd, null);
		tabbedPane.setForegroundAt(2, Color.BLACK);
		//đưa dữ liệu vào bảng
		loadTable();
		
		//load lại bảng khi đổi tab
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) loadTable();
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
	public void loadTable() {
		itemsArrayList = cat.getCategoryObjects();
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
	
	private void deleteCategory(int id) {
		if(!cat.deleteCategory(id)) {
			System.err.println("Xoa khong thanh cong!!!");
			return;
		}
		loadTable();
	}
}
