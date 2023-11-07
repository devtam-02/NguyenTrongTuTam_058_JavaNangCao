package it602003.process;

import java.util.ArrayList;

import it602003.objects.CategoryObject;

public interface Category {

	public boolean addCategory(CategoryObject item);
	
	public boolean updateCategoryObject(CategoryObject item);

	public boolean deleteCategory(int id);
	
	public CategoryObject getCategoryObjectById(int id);
	
	public ArrayList<CategoryObject> getCategoryObjects(byte total);
	
	public ArrayList<CategoryObject> getCategoryObjects();
	
	public ArrayList<CategoryObject> getCategoryObjectByName(String name);

}

