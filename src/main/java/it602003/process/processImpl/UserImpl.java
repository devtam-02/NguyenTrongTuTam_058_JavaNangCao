package it602003.process.processImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.CategoryObject;
import it602003.objects.UserObject;
import it602003.process.User;

public class UserImpl implements User{
	//kết nối để làm việc với csdl
		private Connection con;
		
		//bộ quản lý kết nối riêng section
		private ConnectionPool cp;
		
		public UserImpl() {
			//Xác định bộ quản lý kết nối
			this.cp = new ConnectionPoolImpl();
			
			//Xin kết nối để làm việc
			try {
				this.con = this.cp.getConnection("User");
				
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
		public ArrayList<UserObject> getUserObjects(UserObject similar, byte total){
			
			ArrayList<UserObject> items = new ArrayList<>();
			UserObject item;
			
			String sql = "SELECT user_id, user_fullname FROM tbluser ";
			sql += "";
			sql += "ORDER BY user_name ASC ";
			sql += "LIMIT ?";
			
			//Biên dịch
//			Statement sta = this.con.createStatement();
//			sta.executeQuery(sql);
//			CallableStatement call = this.con.prepareCall(sql);
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				//Truyền giá trị cho tham số
				pre.setByte(1, total);
				
				ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
				if(rs != null) {
					while(rs.next()) {
						item = new UserObject();
						item.setUser_id(rs.getInt("user_id"));
						item.setUser_fullname(rs.getString("user_fullname"));
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
		@Override
		public UserObject getUserObject(int id) {
			UserObject item = new UserObject();
			String sql = "SELECT user_id, user_fullname FROM tbluser WHERE user_id = ? ";
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				pre.setInt(1, id);
				
				ResultSet rs = pre.executeQuery();
				if(rs != null) {
					while(rs.next()) {
						item.setUser_id(rs.getInt("user_id"));
						item.setUser_fullname(rs.getString("user_fullname"));
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return item;
		}
		
}
