package ru.kolivim.estimates.calculator.domain.role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;
import ru.kolivim.estimates.calculator.domain.user.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column
    private String role;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

}
