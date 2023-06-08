package ua.ostrometskiy.diplomaBootJPA.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ostrometskiy.diplomaBootJPA.models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @EntityGraph(attributePaths = {"user", "booksList"})
    Orders findById(int id);
}
