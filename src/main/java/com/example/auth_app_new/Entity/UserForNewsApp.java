package com.example.auth_app_new.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth_app_new.Entity.Abstract.AbstractEntity;
import com.example.auth_app_new.Enumss.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserForNewsApp extends AbstractEntity implements UserDetails{
    

    @Column(unique = true,nullable=false)
    private String phonenumber;


    @Column(nullable=false)
    private String Pasword;

    @Column(nullable=false)
    private String FullName;

    @Column(nullable=false)
    private boolean isAccountNonExpired = true;

    @Column(nullable=false)
    private boolean isAccountNonLocked = true;

    @Column(nullable=false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable=false)
    private boolean isEnabled = false;

    private String verificationCOde;

    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<GrantedAuthority> authoritys = new HashSet<>();
       for (Permission permissions : this.role.getPermissions()) {
        authoritys.add((GrantedAuthority)permissions::name);
       }
       return authoritys;
    }

    @Override
    public String getPassword() {
        return this.Pasword;
    }

    @Override
    public String getUsername() {
        return this.phonenumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    
}
