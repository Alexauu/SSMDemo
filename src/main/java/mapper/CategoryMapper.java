package mapper;
 
import pojo.Category;
import utils.Page;

import java.util.List;


 
public interface CategoryMapper {
 
      
    public int add(Category category);
       
      
    public void delete(int id);  
       
      
    public Category get(int id);  
     
      
    public int update(Category category);   
       
      
    public List<Category> list();
    
    public List<Category> list(Page page);
    
      
    public int total();  
    
    
    
}