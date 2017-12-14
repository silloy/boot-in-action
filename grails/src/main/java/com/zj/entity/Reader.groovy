package com.zj.entity

import grails.persistence.Entity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class Reader implements UserDetails {
    String username
    String fullname
    String password

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        Arrays.asList(new SimpleGrantedAuthority("READER"))
    }

    @Override
    boolean isAccountNonExpired() {
        true
    }

    @Override
    boolean isAccountNonLocked() {
        true
    }

    @Override
    boolean isCredentialsNonExpired() {
        true
    }

    @Override
    boolean isEnabled() {
        true
    }
}