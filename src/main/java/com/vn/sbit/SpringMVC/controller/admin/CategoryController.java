package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.dto.request.RegisterUser;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


//    @GetMapping("/home")
//    public String index(Model model){
//        List<Category> list = categoryService.getAll();
//        model.addAttribute("list_category",list);
//        return "admin/category/index";
//    }
    @GetMapping("/home")
    public String search(Model model, @Param("keyword") String keyword,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo){
        Page<Category> list = categoryService.pagination(pageNo);

        if(keyword !=null){
            list=categoryService.searchAndPagination(keyword,pageNo);
            model.addAttribute("keyword",keyword);
        }

        model.addAttribute("totalPage",list.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("list_category",list);
        return "admin/category/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category",category);
        return "admin/category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") CategoryRequest categoryRequest, BindingResult result, Model model, HttpSession session){
        String categoryName=categoryRequest.getCategoryName();
        if(result.hasErrors()){
            return "admin/category/add";
        }
        Category category = categoryService.findByCategoryName(categoryName);
        if(category != null){
            model.addAttribute("e_category",new RegisterUser());
            model.addAttribute("message_error","Category exist");
            return "admin/category/add";
        }else {
            categoryService.createCategory(categoryRequest);
            session.setAttribute("add_category",category);
            return "redirect:/admin/category/home"; //load controller url
        }
    }
    
    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/category/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id,@ModelAttribute("category") CategoryRequest categoryRequest){
        if(categoryRequest!=null){
           categoryService.updateCategory(id,categoryRequest);
           return "redirect:/admin/category/home"; //load controller url
        }else{
            return "admin/category/edit";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category/home"; //load controller url
    }


}
