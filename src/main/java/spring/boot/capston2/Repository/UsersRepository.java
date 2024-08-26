package spring.boot.capston2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.capston2.Model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findUsersById(Integer id);
}
