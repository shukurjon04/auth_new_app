package com.example.auth_app_new.Entity;

import com.example.auth_app_new.Entity.Abstract.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment extends AbstractEntity{

    private String title;

    @ManyToOne(optional=false)
    private Post post;

}
