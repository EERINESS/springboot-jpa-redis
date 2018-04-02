package com.zzq.test;

import com.zzq.domain.Role;
import com.zzq.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzq
 * @createTime 2018/3/12
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Role> map = new HashMap<>();
        map.put("huhu1", new Role(1L, "总裁"));
        map.put("huhu2", new Role(2L, "总经理"));
        map.put("huhu3", new Role(3L, "经理"));
        map.put("huhu4", new Role(4L, "财务"));
        map.put("huhu5", new Role(5L, "职员"));
        Collection<Role> list = map.values();
        for (Role role : list) {
            System.out.println(role.toString());
        }
    }
}
