package cn.nju.edu.repository;

import cn.nju.edu.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByNameAndPassword(String name, String password);

    User findByName(String name);
}
