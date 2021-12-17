package com.forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
public class UserDetails {
    private static final String BASIC_DESCRIPTION = "No description provided.";

    private String description = BASIC_DESCRIPTION;

    private Boolean sex;

    private LocalDateTime birthDate;

    @Override
    public String toString() {
        return "UserDetails={description=" + description + ", sex=" + sex + ", birthDate=" + birthDate + "}";
    }
}
