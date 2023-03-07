package com.dominik.typer.model.exceptions;

import com.dominik.typer.repository.DbErrorRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;


@Builder
@Getter
@Setter
@ToString
@Entity(name = "dberrors")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbError {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String errorType;
    private String errorMessage;
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DbError dbError = (DbError) o;
        return Id != null && Objects.equals(Id, dbError.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
