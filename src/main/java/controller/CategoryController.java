package controller;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pojo.Category;
import service.CategoryService;
import utils.Page;


// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

//    @RequestMapping("listCategory")
//    public ModelAndView listCategory(Page page){
//
//        ModelAndView mav = new ModelAndView();
//        int limit = 10;
//        PageHelper.offsetPage(page.getStart(),limit);
//        List<Category> cs= categoryService.list();
////        int total = categoryService.total();
//        int total = (int) new PageInfo<>(cs).getTotal();
//        page.caculateLast(total,limit);
//
//        // 放入转发参数
//        mav.addObject("cs", cs);
//        // 放入jsp路径
//        mav.setViewName("listCategory");
//        return mav;
//    }
//
//    @RequestMapping("addCategory")
//    public ModelAndView addCategory(Category category){
//        categoryService.add(category);
//        ModelAndView mav = new ModelAndView("redirect:/listCategory");
//        return mav;
//    }
//    @RequestMapping("deleteCategory")
//    public ModelAndView deleteCategory(Category category){
//        categoryService.delete(category);
//        ModelAndView mav = new ModelAndView("redirect:/listCategory");
//        return mav;
//    }
//    @RequestMapping("editCategory")
//    public ModelAndView editCategory(Category category){
//        Category c= categoryService.get(category.getId());
//        ModelAndView mav = new ModelAndView("editCategory");
//        mav.addObject("c", c);
//        return mav;
//    }
//    @RequestMapping("updateCategory")
//    public ModelAndView updateCategory(Category category){
//        categoryService.update(category);
//        ModelAndView mav = new ModelAndView("redirect:/listCategory");
//        return mav;
//    }


    @ResponseBody
    @RequestMapping("/submitCategory")
    public String submitCategory(@RequestBody String category) {
        Category category1 = (Category)JSON.toJavaObject((JSON)JSONObject.toJSON(JSONObject.parse(category)),Category.class);
        System.out.println("SSM接受到浏览器提交的json，并转换为Category对象:"+category1);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/getOneCategory")
    public String getOneCategory() {
        Category c = new Category();
        c.setId(100);
        c.setName("第100个分类");
        System.out.println(JSONObject.toJSONString(c));
        return JSONObject.toJSONString(c);
    }
    @ResponseBody
    @RequestMapping("/getManyCategory")
    public String getManyCategory() {
        List<Category> cs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Category c = new Category();
            c.setId(i);
            c.setName("分类名称:"+i);
            cs.add(c);
        }

        return JSONObject.toJSONString(cs);
    }



    @RequestMapping(value="/categories",method=RequestMethod.GET)
    public ModelAndView listCategory(Page page){

        ModelAndView mav = new ModelAndView();
        int limit = 10;
        PageHelper.offsetPage(page.getStart(),limit);
        List<Category> cs= categoryService.list();
//        int total = categoryService.total();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.caculateLast(total,limit);

        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }

    @RequestMapping(value="/categories",method=RequestMethod.POST)
    public ModelAndView addCategory(Category category){
        System.out.println("category.getName():"+category.getName());
        categoryService.add(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }

    @RequestMapping(value="/categories/{id}",method=RequestMethod.DELETE)
    public ModelAndView deleteCategory(Category category){
        categoryService.delete(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }
    @RequestMapping(value="/categories/{id}",method= RequestMethod.GET)
    public ModelAndView editCategory(Category category){
        Category c= categoryService.get(category.getId());
        ModelAndView mav = new ModelAndView("editCategory");
        mav.addObject("c", c);
        return mav;
    }
    @RequestMapping(value="/categories/{id}",method=RequestMethod.PUT)
    public ModelAndView updateCategory(Category category){
        categoryService.update(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }



}
