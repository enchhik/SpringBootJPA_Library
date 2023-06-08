package ua.ostrometskiy.diplomaBootJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.diplomaBootJPA.models.Authors;
import ua.ostrometskiy.diplomaBootJPA.repositories.AuthorsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Authors> index() {
        return authorsRepository.findAll();
    }

    public Authors show(int id) {
        return authorsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Authors authors) {
        authorsRepository.save(authors);
    }

    @Transactional
    public void update(int id, Authors updatedAuthors) {
        authorsRepository.findById(id).ifPresent(authorsToBeUpdated -> authorsToBeUpdated.setName(updatedAuthors.getName()));
        authorsRepository.save(updatedAuthors);
    }

    @Transactional
    public void delete(int id) {
        authorsRepository.deleteById(id);
    }
}
