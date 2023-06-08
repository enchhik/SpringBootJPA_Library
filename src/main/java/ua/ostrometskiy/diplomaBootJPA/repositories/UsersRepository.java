package ua.ostrometskiy.diplomaBootJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ostrometskiy.diplomaBootJPA.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
