package cn.nju.edu.dao;

import cn.nju.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByNameAndPassword(String name, String password);

    List<User> findByName(String name);
}
