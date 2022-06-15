package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String categoryName,
                        Model model) {
        Page<CategoryGridDto> allCategories = service.findAllCategory(page, categoryName);
        List<CategoryGridDto> categoryGrid = allCategories.getContent();
        model.addAttribute("categories", categoryGrid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allCategories.getTotalPages());
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("breadCrumbs", "CATEGORY / INDEX");
        return "category/category-index";
    }
}
