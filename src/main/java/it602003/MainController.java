package it602003;
import java.util.ArrayList;

import javax.swing.JComboBox;

import it602003.objects.CategoryObject;
import it602003.objects.SectionObject;
import it602003.objects.UserObject;
import it602003.process.Category;
import it602003.process.Section;
import it602003.process.User;
import it602003.process.processImpl.CategoryImpl;
import it602003.process.processImpl.SectionImpl;
import it602003.process.processImpl.UserImpl;

public class MainController {
	//Tạo đối tượng làm việc với category
	private static Category cat;
	
	
	
	
	public static void main(String[] args) {
		//Khai báo đối tượng làm việc với category
		cat = new CategoryImpl();
		
		//Tạo đối tượng category mới
		CategoryObject catObj = new CategoryObject();
		catObj.setCategory_name("hehehehe");
		catObj.setCategory_created_date("29/10/23");
		cat.addCategory(catObj);
		
		//Lấy danh sách đối tượng
//		ArrayList<CategoryObject> catList = cat.getCategoryObjectByName("");
////		
//		catList.forEach(item ->{
//			System.out.println(item);
//		});
		
		//Lấy ra một đối tượng bằng id
//		CategoryObject catO = cat.getCategoryObjectById(23);
//		System.out.println(catO);
		
		//Xóa đối tượng
//		System.err.println(cat.deleteCategory(52));
//		catO.setCategory_created_author_id(20);
//		cat.updateCategoryObject(catO);
//		Section sec = new SectionImpl();
//		ArrayList<SectionObject> section = sec.getSectionObjects(null,(byte) 10);
//		
//		for (SectionObject sectionObject : section) {
//			System.out.println(sectionObject);
//		}
		
		User user = new UserImpl();
		ArrayList<UserObject> users = user.getUserObjects(null, (byte)20);
		for (UserObject userObject : users) {
			System.out.println(userObject);
		}
		
	}
}
