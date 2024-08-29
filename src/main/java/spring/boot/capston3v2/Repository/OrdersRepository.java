package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findOrdersById(Integer id);

    long countByUsersId(Integer usersId);


}
