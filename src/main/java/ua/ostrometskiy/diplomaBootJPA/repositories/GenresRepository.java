package ua.ostrometskiy.diplomaBootJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ostrometskiy.diplomaBootJPA.models.Genres;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {
}
