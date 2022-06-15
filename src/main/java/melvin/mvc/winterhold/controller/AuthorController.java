package melvin.mvc.winterhold.controller;

import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.AuthorGridDto;
import melvin.mvc.winterhold.model.Author;
import melvin.mvc.winterhold.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
