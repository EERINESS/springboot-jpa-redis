package com.zzq.service;

import com.zzq.domain.User;
import com.zzq.redis.UserRedis;
import com.zzq.redis.UserRedisHash;
import com.zzq.redis.UserRedisMap;
import com.zzq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zzq
 * @createTime 2018/3/9
 * @description 使用 RedisTemplate 做复杂对象缓存
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRedis userRedis;
    @Autowired
    UserRedisHash userRedisHash;
    @Autowired
    UserRedisMap userRedisMap;
    private static final String KEYHEAD = "mysql:get:user:";

    /*
     * 与 Cache 做的事情一样
     */
    @Transactional
    public User save(User user){
        User newUser = userRepository.save(user);
        System.out.println(newUser.toString());
        if (newUser != null && newUser.getUid() != null){
            //userRedis.add(KEYHEAD + newUser.getUid(), 1L, newUser);
            //userRedisHash.add(KEYHEAD + newUser.getUid(), newUser);
            userRedisMap.add(KEYHEAD + newUser.getUid(), newUser);
        }
        return newUser;
    }

    @Transactional
    public User update(User user){
        if (user != null && user.getUid() != null){
            //userRedis.delete(KEYHEAD + user.getUid());
            //userRedisHash.delete(KEYHEAD + user.getUid());
            userRedisMap.delete(KEYHEAD + user.getUid());
            User newUser = userRepository.save(user);
            if (newUser != null && newUser.getUid() != null){
                //userRedis.add(KEYHEAD + user.getUid(), 1L, newUser);
                //userRedisHash.add(KEYHEAD + newUser.getUid(), newUser);
                userRedisMap.add(KEYHEAD + newUser.getUid(), newUser);
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        //User user = userRedisHash.get(KEYHEAD + id);
        User user = userRedisMap.get(KEYHEAD + id);
        if (user == null){
            user = userRepository.findByUid(id);
            //userRedisHash.add(KEYHEAD + user.getUid(), user);
            userRedisMap.add(KEYHEAD + user.getUid(), user);
            System.out.println("从数据库中拿数据");
        } else {
            System.out.println("从缓存中拿数据");
        }
        return user;
    }

    @Transactional
    public User delete(Long id){
        userRepository.delete(id);
        //User user = userRedisHash.get(KEYHEAD + id);
        User user = userRedisMap.get(KEYHEAD + id);
        if (user != null && user.getUid() != null){
            System.out.println("进来了删除");
            //userRedisHash.delete(KEYHEAD + user.getUid());
            userRedisMap.delete(KEYHEAD + user.getUid());
        }
        return user;
    }

    @Transactional
    public List<User> findAll(){
        //List<User> users = userRedisHash.getList();
        List<User> users = userRedisMap.getList();
        if (users == null || users.size() ==0){
            System.out.println("从数据库中拿数据");
            users = userRepository.findAll();
            for (User user : users){
                //userRedisHash.add(KEYHEAD + user.getUid(), user);
                userRedisMap.add(KEYHEAD + user.getUid(), user);
            }
        }
        if (users != null && users.size() != 0) {
            System.out.println("从缓存中拿数据");
        }
        return users;
    }

}
