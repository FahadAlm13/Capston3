package spring.boot.capston3v2.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston3v2.Api.ApiException;
import spring.boot.capston3v2.Model.GardeningTools;
import spring.boot.capston3v2.Model.Orders;
import spring.boot.capston3v2.Model.Plants;
import spring.boot.capston3v2.Model.Users;
import spring.boot.capston3v2.Repository.OrdersRepository;
import spring.boot.capston3v2.Repository.PlantsRepository;
import spring.boot.capston3v2.Repository.UsersRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final PlantsRepository plantsRepository;

    private static final double DISCOUNT_PERCENTAGE = 10.0;


    public List<Orders> getAllOrders() {

        return ordersRepository.findAll();
    }


    public void addOrder(Orders orders, Integer users_id, Integer plants_id) {
        Users users = usersRepository.findUsersById(users_id);
        Plants plants = plantsRepository.findPlantsById(plants_id);
        if(users==null || plants==null){
            throw new ApiException("Cannot assign user and plant to orders");
        }
        if (plants.getStock() < orders.getQuantity()) {
            throw new ApiException("Insufficient stock for plant: " + plants.getName());
        }


        plants.setStock(plants.getStock() - orders.getQuantity());


        orders.setUsers(users);

        orders.getPlants().add(plants);
        plants.getOrders().add(orders);

        double totalAmount = plants.getPrice() * orders.getQuantity();
        orders.setTotalAmount(totalAmount);

        long userOrderCount = ordersRepository.countByUsersId(users_id);
        if (userOrderCount >= 3) {
            double discount = totalAmount * (DISCOUNT_PERCENTAGE / 100);
            orders.setTotalAmount(totalAmount - discount);
        }
        orders.setOrderDate(LocalDate.now());
        orders.setUpdatedAt(LocalDate.now());
        ordersRepository.save(orders);
    }

    public void updateOrder(Integer id,Orders orders) {
        Orders order1 = ordersRepository.findOrdersById(id);

        if (order1 == null) {
            throw new ApiException("Order id not found");
        }

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
    public void applyDiscountToOrder(Integer orderId, double discountPercentage) {
        Orders order = ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new ApiException("Order not found");
        }

        double discountAmount = order.getTotalAmount() * (discountPercentage / 100);
        double newTotalAmount = order.getTotalAmount() - discountAmount;

        order.setDiscount(discountAmount);
        order.setTotalAmount(newTotalAmount);
        order.setUpdatedAt(LocalDate.now());

        ordersRepository.save(order);
    }
    //1
//    public double calculateTotalAmount(Integer orderId) {
//        Orders order = ordersRepository.findOrdersById(orderId);
//        if (order == null) {
//            throw new ApiException("Order not found");
//        }
//        double totalAmount = 0.0;
//        // Calculate total for plants
//        if (order.getPlants() != null && !order.getPlants().isEmpty()) {
//            for (Plants plant : order.getPlants()) {
//                totalAmount += plant.getPrice() * order.getQuantity();
//            }
//        }
//        // Calculate total for gardening tools
//        if (order.getGardeningTools() != null && !order.getGardeningTools().isEmpty()) {
//            for (GardeningTools tool : order.getGardeningTools()) {
//                totalAmount += tool.getPrice() * order.getQuantity();
//            }
//        }
//        return totalAmount;
//    }



}
