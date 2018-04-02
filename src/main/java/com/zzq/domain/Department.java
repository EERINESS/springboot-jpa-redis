package com.zzq.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zzq
 * @createTime 2018/3/8
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long depid;
    private String depname;

    public Department() {
    }

    public Department(String depname) {
        this.depname = depname;
    }

    public Long getDepid() {
        return depid;
    }

    public void setDepid(Long depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depid=" + depid +
                ", depname='" + depname + '\'' +
                '}';
    }
}
