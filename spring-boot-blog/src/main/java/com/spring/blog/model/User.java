package com.spring.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.blog.validator.PasswordValidator;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@PasswordValidator
@Data
public class User extends BaseModel {

    @Column(unique = true,
            nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(unique = true,
            nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
