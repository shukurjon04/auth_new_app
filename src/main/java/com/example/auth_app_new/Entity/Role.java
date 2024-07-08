package com.example.auth_app_new.Entity;

import java.util.Set;

import com.example.auth_app_new.Entity.Abstract.AbstractEntity;
import com.example.auth_app_new.Enumss.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {

    @Column(nullable=false,unique=true)
    private String name;
    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch=FetchType.LAZY)
    private Set<Permission> permissions;

}
