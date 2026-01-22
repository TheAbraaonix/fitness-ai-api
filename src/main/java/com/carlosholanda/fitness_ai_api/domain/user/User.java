package com.carlosholanda.fitness_ai_api.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.carlosholanda.fitness_ai_api.domain.user.enums.FitnessGoal;
import com.carlosholanda.fitness_ai_api.domain.user.enums.FitnessLevel;
import com.carlosholanda.fitness_ai_api.domain.user.enums.UserRole;

public class User {
    private UUID id;

    // Personal info (mandatory)
    private String name;

    private String email;

    private String password;

    private UserRole role = UserRole.USER;

    // Personal info (optional)
    private Integer age;
    private Double weight;
    private Double height;
    private Integer availableDaysPerWeek;

    private FitnessGoal fitnessGoal;

    private FitnessLevel fitnessLevel;

    // User Control
    private boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public User() {
    }

    public User(UUID id, String name, String email, String password, UserRole role, Integer age, Double weight, Double height, Integer availableDaysPerWeek, FitnessGoal fitnessGoal, FitnessLevel fitnessLevel, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.availableDaysPerWeek = availableDaysPerWeek;
        this.fitnessGoal = fitnessGoal;
        this.fitnessLevel = fitnessLevel;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Integer getAvailableDaysPerWeek() {
        return availableDaysPerWeek;
    }

    public FitnessGoal getFitnessGoal() {
        return fitnessGoal;
    }

    public FitnessLevel getFitnessLevel() {
        return fitnessLevel;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setAvailableDaysPerWeek(Integer availableDaysPerWeek) {
        this.availableDaysPerWeek = availableDaysPerWeek;
    }

    public void setFitnessGoal(FitnessGoal fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setFitnessLevel(FitnessLevel fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
