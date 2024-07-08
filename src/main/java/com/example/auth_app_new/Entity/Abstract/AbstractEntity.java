package com.example.auth_app_new.Entity.Abstract;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.auth_app_new.Entity.UserForNewsApp;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,updatable=false)
    @CreationTimestamp
    private Timestamp CreatedAt;

    @Column(nullable=false)
    @UpdateTimestamp
    private Timestamp UpdateAt;

    @JoinColumn(updatable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    @CreatedBy
    private UserForNewsApp CreatedBy;

    @ManyToOne(fetch= FetchType.LAZY)
    @LastModifiedBy
    private UserForNewsApp UpdatedBy;


}
