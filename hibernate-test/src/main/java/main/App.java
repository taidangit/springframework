package main;

import java.util.List;

import dao.CategoryDAO;
import model.Category114;

public class App {
	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAO();
		//categoryDAO.insert(new Category114("Xiaomi"));
		
		
//		 List<Category114> category114s = categoryDAO.getData(); 
//		 for(Category114 cat : category114s) { 
//			 System.out.println(cat.getCategoryId()+"-"+cat.getName()); 
//		 }
		 
		//Category114 cat = categoryDAO.getCategoryById(4l);
		//System.out.println(cat.getCategoryId()+"-"+cat.getName());
		
//		 Category114 cat = categoryDAO.getCategoryById(4l);
//		 cat.setName("Nokia");
//		 categoryDAO.update(cat); 
		
		categoryDAO.deleteCategory(4l);
		 
		 
	}
}
