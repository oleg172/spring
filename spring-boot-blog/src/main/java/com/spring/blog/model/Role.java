package com.spring.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel {

    @Column(name = "name")
    private String name;
}
