package it602003.process;

import java.sql.*;
import java.util.*;
import it602003.objects.*;
import it602003.*;

public class Section {
	//kết nối để làm việc với csdl
	private Connection con;
	
	//bộ quản lý kết nối riêng section
	private ConnectionPool cp;
	
	public Section() {
		//Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();
		
		//Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection("Section");
			
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
	public ArrayList<SectionObject> getSectionObjects(SectionObject similar, byte total){
		
		ArrayList<SectionObject> items = new ArrayList<>();
		SectionObject item;
		
		String sql = "SELECT * FROM tblsection ";
		sql += "";
		sql += "ORDER BY section_name ASC ";
		sql += "LIMIT ?";
		
		//Biên dịch
//		Statement sta = this.con.createStatement();
//		sta.executeQuery(sql);
//		CallableStatement call = this.con.prepareCall(sql);
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			//Truyền giá trị cho tham số
			pre.setByte(1, total);
			
			ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
			if(rs != null) {
				while(rs.next()) {
					item = new SectionObject();
//					item.setSection_id(rs.getShort(1));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					
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
	
	public static void main(String[] args) {
		//tạo đối tượng làm việc với section
		Section s = new Section();
		
		//Lấy danh sách đối tượng
		ArrayList<SectionObject> itemsArrayList = s.getSectionObjects(null, (byte) 5);
		
		//in ra màn hình

		itemsArrayList.forEach(item -> {
			System.out.println(item);
		});
	}
}
