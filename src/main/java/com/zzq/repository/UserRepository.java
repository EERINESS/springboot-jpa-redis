package com.zzq.repository;

import com.zzq.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zzq
 * @createTime 2018/3/8
 * @decription 继承 repository 接口，获得支持
 *             原理：单顶层接口 + 抽象类 + 本接口 + 代理本接口的实现类
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(Long uid);

    //And 用法
    User findByUidAndUsername(Long uid, String username);

    //Or 用法
    User findByUidOrUsername(Long uid, String username);

    //Between 用法
    User findByCreateDateBetween(Date start, Date end);

    //LessThan 用法 （不到）
    User findByCreateDateLessThan(Date start);

    //GreaterThan 用法 （大于）
    User findByCreateDateGreaterThan(Date start);

    //IsNull / IsNotNull 用法
    List<User> findByUsernameIsNull(String username);

    //Like / NotList 用法
    List<User> findByUsernameLike(String username);

    //OrderBy 用法 （排序）
    List<User> findByUsernameOrderByUidAsc(String username);

    //Not 用法
    List<User> findByUsernameNot(String username);

    //In / NotIn 用法
    List<User> findByUsernameIn(Collection<String> nameList);

}
