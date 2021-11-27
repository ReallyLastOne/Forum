package com.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @NotNull
    private String password;

    @Column(unique = true)
    @Email
    @NotNull
    @NotBlank
    private String email;


}
