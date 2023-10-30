package it602003.process.processImpl;

import java.sql.*;
import java.util.*;
import it602003.objects.*;
import it602003.process.Category;
import it602003.*;

public class CategoryImpl implements Category{
	//kết nối để làm việc với csdl
	private Connection con;
	
	//bộ quản lý kết nối riêng section
	private ConnectionPool cp;
	
	public CategoryImpl() {
		//Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();
		
		//Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection("Category");
			
			//Kiểm tra chế độ thực thi của kết nối
			if(this.con.getAutoCommit()) {
				//Hủy chế độ thực thi tự động
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<CategoryObject> getCategoryObjects(byte total){
		
		ArrayList<CategoryObject> items = new ArrayList<>();
		CategoryObject item;
		
		String sql = "SELECT * FROM tblcategory ";
		sql += "";
		sql += "ORDER BY category_name ASC ";
		sql += "LIMIT ?";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			//Truyền giá trị cho tham số
			pre.setByte(1, total);
			
			ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
			if(rs != null) {
				while(rs.next()) {
					item = new CategoryObject();
					item.setCategory_id((rs.getShort("category_id")));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getByte("category_language"));
					
					items.add(item);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//trở về trạng thái an toàn của kết nối
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	
		return items;
	}
	
	public ArrayList<CategoryObject> getCategoryObjectByName(String name){
		
		ArrayList<CategoryObject> items = new ArrayList<>();
		CategoryObject item;
		
		String sql = "SELECT * FROM tblcategory ";
		sql += "WHERE category_name LIKE ? ";
		sql += "ORDER BY category_name ASC";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			//Truyền giá trị cho tham số
			pre.setString(1, "%" + name + "%");
			ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
			if(rs != null) {
				while(rs.next()) {
					item = new CategoryObject();
					item.setCategory_id((rs.getShort("category_id")));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getByte("category_language"));
					
					items.add(item);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//trở về trạng thái an toàn của kết nối
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return items;
	}
	
	public boolean addCategory(CategoryObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcategory(");
		sql.append("category_name, category_section_id, ");
		sql.append("category_notes, category_created_date, category_created_author_id, ");
		sql.append("category_last_modified, category_manager_id, category_enable, ");
		sql.append("category_delete, category_image, category_name_en, category_language) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setByte(12, item.getCategory_language());
			//thực thi
			int result = pre.executeUpdate();
			if (result==0) {
				this.con.rollback();
				return false;
			}
			
			//Ghi nhận thực thi sau cùng
			this.con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public CategoryObject getCategoryObjectById(int id) {
		CategoryObject item = new CategoryObject();
		String sql = "SELECT * FROM tblcategory WHERE category_id = ? ";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setShort(1, (short)id);
			
			ResultSet rs = pre.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					item = new CategoryObject();
					item.setCategory_id((rs.getShort("category_id")));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getByte("category_language"));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return item;
	}
	@Override
	public boolean updateCategoryObject(CategoryObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblcategory SET ");
		sql.append("category_name = ?, category_section_id = ?, ");
		sql.append("category_notes = ?, category_created_date = ?, category_created_author_id = ?, ");
		sql.append("category_last_modified = ?, category_manager_id = ?, category_enable = ?, ");
		sql.append("category_delete = ?, category_image = ?, category_name_en = ?, category_language = ? ");
		sql.append("WHERE category_id = ?;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setByte(12, item.getCategory_language());
			pre.setShort(13, item.getCategory_id());
			//thực thi
			int result = pre.executeUpdate();
			if (result==0) {
				this.con.rollback();
				return false;
			}
			
			//Ghi nhận thực thi sau cùng
			this.con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteCategory(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblcategory WHERE category_id = ?;");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setShort(1, (short) id);
			int result = pre.executeUpdate();
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
