package com.octoSecurity.OctoSecurity;


import javax.persistence.*;


@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private Integer age;
    private String password;
    private Boolean status;
    @Column(name = "profile_role")
    @Enumerated(EnumType.STRING)
    private ProfileRole profileRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProfileRole getProfileRole() {
        return profileRole;
    }

    public void setProfileRole(ProfileRole profileRole) {
        this.profileRole = profileRole;
    }
}
