package com.scqzy.base.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/13 13:06
 */
@Entity
@Table(name = "tb_label")
@Getter
@Setter
@ToString
public class Label implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "label_name")
    private String labelName;
    @Column(name = "state")
    private String state;
    @Column(name = "count")
    private Long count;
    @Column(name = "fans")
    private Long fans;
    @Column(name = "recommend")
    private String recommend;
}
