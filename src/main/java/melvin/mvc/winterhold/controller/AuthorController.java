package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.AuthorGridDto;
import melvin.mvc.winterhold.dto.author.UpsertAuthorDto;
import melvin.mvc.winterhold.dto.book.BookByAuthorDetailsDto;
import melvin.mvc.winterhold.service.author.AuthorService;
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
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model) {
        Page<AuthorDto> allAuthor = service.findAllAuthor(page, fullName);
        List<AuthorGridDto> authorGrid = AuthorGridDto.toList(allAuthor.getContent());
        model.addAttribute("authors", authorGrid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", allAuthor.getTotalPages());
        model.addAttribute("fullName", fullName);
        model.addAttribute("breadCrumbs", "AUTHOR / INDEX");
        return "author/author-index";
    }

    @GetMapping("upsert-form")
    public String upsertForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("author", service.findAuthorById(id));
            model.addAttribute("breadCrumbs", "AUTHOR / UPDATE AUTHOR");
        } else {
            model.addAttribute("author", new UpsertAuthorDto());
            model.addAttribute("breadCrumbs", "AUTHOR / INSERT NEW AUTHOR");
        }
        return "author/author-form";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("author") UpsertAuthorDto author,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getRawFieldValue("id") == null) {
                model.addAttribute("breadCrumbs", "AUTHOR / INSERT NEW AUTHOR");
            } else {
                model.addAttribute("breadCrumbs", "AUTHOR / UPDATE AUTHOR");
            }
            model.addAttribute("author", author);
            return "author/author-form";
        }
        service.saveAuthor(author);
        redirectAttributes.addFlashAttribute("SUCCESS",
                "Author's data have been saved");
        return "redirect:/author/index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        if (id != null) {
            try {
                service.deleteAuthorById(id);
                redirectAttributes.addFlashAttribute("SUCCESS", "Author has been deleted");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("ERROR",
                        "Author's book still available in this library, can not delete author!");
            }
        }
        return "redirect:/author/index";
    }

    @GetMapping("books")
    public String authorBooks(@RequestParam(required = false) Long id,
                              @RequestParam(defaultValue = "1") int page,
                              Model model) {
        UpsertAuthorDto author = service.findAuthorById(id);
        String fullName = String.format("%s. %s %s",
                author.getTitle(),
                author.getFirstName(),
                author.getLastName());
        String authorBirthDate = service.dateFormatIndonesia(author.getBirthDate());
        String authorDeceasedDate = author.getDeceasedDate() == null?
                "-" : service.dateFormatIndonesia(author.getDeceasedDate());
        Page<BookByAuthorDetailsDto> authorBooks = service.findBooksByAuthor(id, page);
        List<BookByAuthorDetailsDto> authorBooksGrid = authorBooks.getContent();

        model.addAttribute("author", author);
        model.addAttribute("fullName", fullName);
        model.addAttribute("birthDate", authorBirthDate);
        model.addAttribute("deceasedDate", authorDeceasedDate);
        model.addAttribute("books", authorBooksGrid);
        model.addAttribute("breadCrumbs", "AUTHOR'S BOOK(S)");
        model.addAttribute("page", page);
        model.addAttribute("totalPage", authorBooks.getTotalPages());

        return "author/author-book";
    }
}
