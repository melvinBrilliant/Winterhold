package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.author.AuthorDropDownDto;
import melvin.mvc.winterhold.dto.book.BookByCateogryDto;
import melvin.mvc.winterhold.dto.book.UpsertBookDto;
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

    @GetMapping("books")
    public String categoryBooks(@RequestParam(required = false) String categoryName,
                                @RequestParam(defaultValue = "") String bookTitle,
                                @RequestParam(defaultValue = "") String authorName,
                                @RequestParam(defaultValue = "1") int page,
                                Model model) {
        Page<BookByCateogryDto> allBooksByCategory = service.findBookByCategory(categoryName, bookTitle, authorName, page);
        List<BookByCateogryDto> booksByCategoryGrid = allBooksByCategory.getContent();
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("authorName", authorName);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryBooks", booksByCategoryGrid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allBooksByCategory.getTotalPages());
        model.addAttribute("breadCrumbs", "CATEGORY: " + categoryName);
        return "category/category-book";
    }

    @GetMapping("books/upsert-form")
    public String upsertBooks(@RequestParam(required = false) String bookId,
                              @RequestParam String categoryName,
                              Model model) {
        List<AuthorDropDownDto> authors = service.findAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("categoryName", categoryName);
        if (bookId != null) {
            model.addAttribute("book", service.findBookById(bookId));
            model.addAttribute("breadCrumbs", "BOOK / UPDATE BOOK");
        } else {
            model.addAttribute("book", new UpsertBookDto());
            model.addAttribute("breadCrumbs", "BOOK / INSERT NEW BOOK");
        }
        return "category/category-book-form";
    }

    @PostMapping("books/upsert")
    public String upsert(@Valid @ModelAttribute("book") UpsertBookDto bookDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getRawFieldValue("bookId") == "") {
                model.addAttribute("breadCrumbs", "BOOK / INSERT NEW BOOK");
            } else {
                model.addAttribute("breadCrumbs", "BOOK / UPDATE BOOK");
            }
            model.addAttribute("book", bookDto);
            return "category/category-book-form";
        }
        service.saveBook(bookDto);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Book has been saved");
        return "redirect:/category/index";
        // todo: redirect ke halaman index book by category recent
    }
}
