package ua.ostrometskiy.diplomaBootJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.diplomaBootJPA.models.Orders;
import ua.ostrometskiy.diplomaBootJPA.repositories.OrdersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> index() {
        return ordersRepository.findAll();
    }

    public Orders show(int id) {
        return ordersRepository.findById(id);
    }

    @Transactional
    public void save(Orders orders) {
        ordersRepository.save(orders);
    }

    @Transactional
    public void update(int id, Orders updatedOrders) {
        Orders ordersToBeUpdated = ordersRepository.findById(id);

        ordersToBeUpdated.setUser(updatedOrders.getUser());
        ordersToBeUpdated.setBooksList(updatedOrders.getBooksList());
        ordersToBeUpdated.setOrderDate(updatedOrders.getOrderDate());

        ordersRepository.save(ordersToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

}
