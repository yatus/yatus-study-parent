package cn.yatus.chapter1.repository;

import cn.yatus.chapter1.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
