package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.category.CategoryDto;
import melvin.mvc.winterhold.dto.category.CategoryGridDto;
import melvin.mvc.winterhold.dto.category.CategoryUpsertDto;
import melvin.mvc.winterhold.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        Page<CategoryDto> allCategories = service.findAllCategory(page, categoryName);
        List<CategoryGridDto> categoryGrid = CategoryGridDto.toList(allCategories.getContent());
        model.addAttribute("categories", categoryGrid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allCategories.getTotalPages());
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("breadCrumbs", "CATEGORY / INDEX");
        return "category/category-index";
    }

    @GetMapping("upsert-form")
    public String upsertForm(@RequestParam(required = false) String categoryName, Model model) {
        if (categoryName != null) { // categoryName = id
            model.addAttribute("category", service.findCategoryById(categoryName));
            model.addAttribute("breadCrumbs", "CATEGORY / UPDATE CATEGORY");
        } else {
            model.addAttribute("category", new CategoryGridDto());
            model.addAttribute("breadCrumbs", "CATEGORY / INSERT NEW CATEGORY");
        }
        return "category/category-form";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("category") CategoryUpsertDto category,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getRawFieldValue("categoryName") == "") {
                model.addAttribute("breadCrumbs", "CATEGORY / INSERT NEW CATEOGRY");
            } else {
                model.addAttribute("breadCrumbs", "CATEGORY / UPDATE CATEGORY");
            }
            model.addAttribute("category", category);
            return "category/category-form";
        }
        service.saveCategory(category);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Category has been saved");
        return "redirect:/category/index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String categoryName, RedirectAttributes redirectAttributes) {
        if (categoryName != null) { // categoryName = id
            try {
                service.deleteCategoryById(categoryName);
                redirectAttributes.addFlashAttribute("SUCCESS",
                        "Category has been deleted");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("ERROR",
                        "Can not delete category with book(s) in it!");
            }
        }
        return "redirect:/category/index";
    }
}
