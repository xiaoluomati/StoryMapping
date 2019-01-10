package cn.nju.edu.repository;

import cn.nju.edu.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByNameAndPassword(String name, String password);

    List<User> findByName(String name);
}
