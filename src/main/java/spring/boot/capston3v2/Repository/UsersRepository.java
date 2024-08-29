package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.capston3v2.Model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findUsersById(Integer id);
}
