package ua.ostrometskiy.diplomaBootJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.diplomaBootJPA.models.Books;
import ua.ostrometskiy.diplomaBootJPA.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> index() {
        return booksRepository.findAll();
    }

    public Books show(int id) {
        return booksRepository.findById(id);
    }

    @Transactional
    public void save(Books books){
        booksRepository.save(books);
    }

    @Transactional
    public void update(int id, Books updatedBooks) {
        Books booksToBeUpdated = booksRepository.findById(id);

        booksToBeUpdated.setTitle(updatedBooks.getTitle());
        booksToBeUpdated.setIsbn(updatedBooks.getIsbn());
        booksToBeUpdated.setReleaseDate(updatedBooks.getReleaseDate());
        booksToBeUpdated.setGenre(updatedBooks.getGenre());
        booksToBeUpdated.setAuthorList(updatedBooks.getAuthorList());

        booksRepository.save(updatedBooks);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

}
