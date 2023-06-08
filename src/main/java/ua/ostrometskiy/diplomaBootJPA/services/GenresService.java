package ua.ostrometskiy.diplomaBootJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.diplomaBootJPA.models.Genres;
import ua.ostrometskiy.diplomaBootJPA.repositories.GenresRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genres> index() {
        return genresRepository.findAll();
    }

    public Genres show(int id) {
        return genresRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Genres genres) {
        genresRepository.save(genres);
    }

    @Transactional
    public void update(int id, Genres updatedGenres) {
        genresRepository.findById(id).ifPresent(genresToBeUpdated -> genresToBeUpdated.setName(updatedGenres.getName()));
        genresRepository.save(updatedGenres);
    }

    @Transactional
    public void delete(int id) {
        genresRepository.deleteById(id);
    }

}
