package com.company.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private UserStatus status;
    @OneToMany(mappedBy="user")
    Set<Order> orders;

    public User() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Set<Order> getOrders() {
        return orders;
    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withLogin(String login){
            user.login = login;
            return this;
        }
        public Builder withPassword(String password){
            user.password = password;
            return this;
        }
        public Builder withRole(Role role){
            user.role = role;
            return this;
        }
        public Builder withFirstName(String name){
            user.firstName = name;
            return this;
        }
        public Builder withLastName(String name){
            user.lastName = name;
            return this;
        }
        public Builder withEmail(String email){
            user.email = email;
            return this;
        }
        public Builder withAvatar(String avatar){
            user.avatarUrl = avatar;
            return this;
        }
        public Builder withOrders(Set<Order> orders){
            user.orders = orders;
            return this;
        }
        public Builder withStatus(UserStatus userStatus){
            user.status = userStatus;
            return this;
        }
        public User build(){
            return user;
        }
    }
}
