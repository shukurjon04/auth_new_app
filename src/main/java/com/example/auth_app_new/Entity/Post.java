package com.example.auth_app_new.Entity;

import com.example.auth_app_new.Entity.Abstract.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post extends AbstractEntity {

    @Column(nullable=false)
    private String name;
    @Column(columnDefinition="text")
    private String titli;

}
