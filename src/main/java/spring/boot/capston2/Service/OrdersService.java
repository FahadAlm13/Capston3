package spring.boot.capston2.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston2.API.ApiException;
import spring.boot.capston2.Model.Orders;
import spring.boot.capston2.Model.Users;
import spring.boot.capston2.Repository.OrdersRepository;
import spring.boot.capston2.Repository.UsersRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void addOrder(Orders orders) {
        orders.setOrderDate(LocalDate.now());
        orders.setUpdatedAt(LocalDate.now());
        ordersRepository.save(orders);
    }

    public void updateOrder(Integer id, Orders orders) {
        Orders order1 = ordersRepository.findOrdersById(id);

        if (order1 == null) {
            throw new ApiException("Order id not found");
        }
        order1.setUser_id(orders.getUser_id());
//        order1.setPlant_id(orders.getPlant_id());

        order1.setTotalAmount(orders.getTotalAmount());
        order1.setItems(orders.getItems());
        order1.setQuantity(orders.getQuantity());
        order1.setToolStatus(orders.isToolStatus());
        order1.setUpdatedAt(LocalDate.now());
        ordersRepository.save(order1);
    }


    public void deleteOrder(Integer id) {
        Orders order1 = ordersRepository.findOrdersById(id);
        if (order1 == null) {
            throw new ApiException("Order id not found");
        }
        ordersRepository.delete(order1);
    }

    public void assignUsersToOrders(Integer id, Integer usersId) {
        Orders orders = ordersRepository.findOrdersById(id);
        Users users = usersRepository.findUsersById(usersId);
        if (orders == null) {
            throw new ApiException("Orders not found");
        }
        if (users == null) {
            throw new ApiException("user not found");
        }
        orders.setUsers(users);

        ordersRepository.save(orders);
    }
}
