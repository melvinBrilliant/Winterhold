package melvin.mvc.winterhold.service.author;

import melvin.mvc.winterhold.dao.AuthorRepository;
import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.AuthorGridDto;
import melvin.mvc.winterhold.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    private final int PAGE_LIMIT = 5;

    public Page<AuthorDto> findAllAuthor(Integer page, String fullName){
        Pageable pageable = PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id"));
        return authorRepository.findAll(fullName, pageable);
    }
}