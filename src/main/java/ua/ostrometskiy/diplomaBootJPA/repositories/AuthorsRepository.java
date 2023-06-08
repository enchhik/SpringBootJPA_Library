package ua.ostrometskiy.diplomaBootJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ostrometskiy.diplomaBootJPA.models.Authors;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
}
