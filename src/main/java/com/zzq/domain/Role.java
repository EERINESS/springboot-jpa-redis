package com.zzq.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zzq
 * @createTime 2018/3/8
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long rid;
    private String rname;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Role() {
    }

    public Role(Long rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                '}';
    }
}
