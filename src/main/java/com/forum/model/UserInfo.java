package com.forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
public class UserInfo {
    private static final String BASIC_DESCRIPTION = "No description provided.";

    private String description = BASIC_DESCRIPTION;

    private Boolean sex;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthDate;

    public void setBirthDate(String birthDate) {
        if (this.birthDate == null) this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserInfo={description=" + description + ", sex=" + sex + ", birthDate=" + birthDate + "}";
    }
}
