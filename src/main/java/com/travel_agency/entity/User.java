package com.travel_agency.entity;

import java.util.Objects;

public class User extends Entity {
    private String name;
    private String surname;
    private String login;
    private String password;
    private double money;
    private String email;
    private RoleType role;

    public User() {
    }

    public User(int id, String name, String surname, String login,
                String password, double money, String email, RoleType role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.money = money;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }

    public String getEmail() {
        return email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        User user = (User) o;
        return Double.compare(user.money, money) == 0 && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname) && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, login, password, money, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
