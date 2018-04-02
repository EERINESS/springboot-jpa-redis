package com.zzq.redis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zzq
 * @createTime 2018/3/8
 * @decription redis持久化类
 */
@Repository
public class UserRedisHash {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String KEY = "UserHashKey";


    public User get(String key){
        Object obj = redisTemplate.opsForHash().get(KEY, key);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    public List<User> getList(){
        List<User> list =null;
        if (list != null) {
            List<Object> userList = redisTemplate.opsForHash().values(KEY);
        }

        return list;
    }

    public void delete(String key){
        redisTemplate.opsForHash().delete(KEY,key);

    }
}
