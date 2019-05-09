package cn.yatus.chapter1.service;

import cn.yatus.chapter1.annotation.Slave;
import cn.yatus.chapter1.dto.UserDto;
import cn.yatus.chapter1.entity.User;
import cn.yatus.chapter1.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(UserDto user) {
        User target = new User();
        BeanUtils.copyProperties(user,target);
        target.setId(null);
        userRepository.save(target);
    }


    @Slave
    public Iterable<User> list() {
       return userRepository.findAll();
    }
}
