package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private int age;
    private String email;
    private String username;
    private String password;

    @ManyToMany
    //отдельная связывающая таблица
    @JoinTable(name = "users_roles",
            //колонка, которая принадлежит текущей сущности:
            joinColumns = @JoinColumn(name = "users_id"),
            //колонка, которая принадлежит Role:
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Возвращает логин нашей сущности (Для Spring Security):
    @Override
    public String getUsername() {
        return username;
    }

    //Возвращает пароль нашей сущности (Для Spring Security):
    @Override
    public String getPassword() {
        return password;
    }

    //Для авторизации:
    //Список прав Usera:
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    //Аккаунт не просрочен:
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Аккаунт не заблокирован:
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Пароль не просрочен:
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Аккаунт "включен"
    @Override
    public boolean isEnabled() {
        return true;
    }

}
