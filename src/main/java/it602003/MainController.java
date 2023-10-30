package it602003;
import java.util.ArrayList;

import it602003.objects.CategoryObject;
import it602003.objects.SectionObject;
import it602003.process.Category;
import it602003.process.processImpl.CategoryImpl;
import it602003.process.processImpl.SectionImpl;

public class MainController {
	
	
	
	public static void main(String[] args) {
		//tạo đối tượng làm việc với section
		SectionImpl s = new SectionImpl();
		
		//Tạo đối tượng chuyên mục mới
		SectionObject nsec = new SectionObject();
		nsec.setSection_name("Lap trinh java Lâng Kao");
		nsec.setSection_created_date("27/10/23");
		nsec.setSection_created_author_id(20);
		
//		if(!s.addSection(nsec)) {
//			System.out.println("----KHÔNG THÀNH CÔNG----");
//		}
		
		//Lấy danh sách đối tượng
		ArrayList<SectionObject> itemsArrayList = s.getSectionObjects(null, (byte) 20);

		
		//in ra màn hình

//		itemsArrayList.forEach(item -> {
//			System.out.println(item);
//		});
		
		Category cat = new CategoryImpl();
		
		CategoryObject catObj = new CategoryObject();
		catObj.setCategory_name("hehehehe");
		catObj.setCategory_created_date("29/10/23");
//		cat.addCategory(catObj);
		
//		ArrayList<CategoryObject> catList = cat.getCategoryObjectByName("");
////		
//		catList.forEach(item ->{
//			System.out.println(item);
//		});
		
		CategoryObject catO = cat.getCategoryObjectById(23);
		System.out.println(catO);
		
//		System.err.println(cat.deleteCategory(52));
//		catO.setCategory_created_author_id(20);
//		cat.updateCategoryObject(catO);
		
	}
}
