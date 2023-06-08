package ua.ostrometskiy.diplomaBootJPA.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ostrometskiy.diplomaBootJPA.models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    @EntityGraph(attributePaths = {"genre", "authorList"})
    Books findById(int id);
}
