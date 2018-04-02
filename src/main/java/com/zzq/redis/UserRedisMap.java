package com.zzq.redis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author zzq
 * @createTime 2018/3/12
 * @description redis 持久化类
 */
@Repository
public class UserRedisMap {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "UserStringKey";

    public void add(String key, User user){
        ValueOperations<String, Map<String, User>> mapValueOperations = redisTemplate.opsForValue();
        Map<String, User> map = mapValueOperations.get(KEY);
        if (map == null){
            map = new HashMap<>();
        }
        map.put(key, user);
        mapValueOperations.set(KEY, map);   // 把 Map 序列化成字节数组
    }


    public User get(String key){
        ValueOperations<String, Map<String, User>> mapValueOperations = redisTemplate.opsForValue();
        Map<String, User> map = mapValueOperations.get(KEY);    //把 Map 对应的字节数据反序列化成 Map
        User user = map.get(key);
        return user;
    }

    public List<User> getList(){
        List<User> list = null;
        ValueOperations<String, Map<String, User>> mapValueOperations = redisTemplate.opsForValue();
        Map<String, User> map = mapValueOperations.get(KEY);    //把 Map 对应的字节数据反序列化成 Map
        if (map != null){
            list = (new ArrayList<User>(map.values())) ;
        }
        return list;
    }

    public void delete(String key){
        ValueOperations<String, Map<String, User>> mapValueOperations = redisTemplate.opsForValue();
        Map<String, User> map = mapValueOperations.get(KEY);    //把 Map 对应的字节数据反序列化成 Map
        map.remove(key);
        mapValueOperations.set(KEY, map);
    }
}
