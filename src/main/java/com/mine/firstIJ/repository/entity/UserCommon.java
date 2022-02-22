package com.mine.firstIJ.repository.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(schema = "user_schema", name = "user_common")
@IdClass(UserCommonId.class)
public class UserCommon {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "password_token")
    private String passwordEncrypted;
}
