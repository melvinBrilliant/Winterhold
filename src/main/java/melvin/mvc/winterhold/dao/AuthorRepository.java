package melvin.mvc.winterhold.dao;

import melvin.mvc.winterhold.dto.author.AuthorDto;
import melvin.mvc.winterhold.dto.author.AuthorGridDto;
import melvin.mvc.winterhold.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("""
            SELECT new melvin.mvc.winterhold.dto.author.AuthorDto (
                a.id,
                a.title,
                a.firstName,
                a.lastName,
                a.birthDate,
                a.deceasedDate,
                a.education,
                a.summary
            )
            FROM Author a
            WHERE CONCAT(a.title, ' ', a.firstName, ' ', a.lastName) LIKE %:fullName%
            """)
    Page<AuthorDto> findAll(String fullName, Pageable pageable);
}