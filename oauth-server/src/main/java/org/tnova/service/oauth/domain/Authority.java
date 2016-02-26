package org.tnova.service.oauth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority
{

    @Id @GeneratedValue( strategy = GenerationType.AUTO ) private Long id;

    private String authority;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @Override public String getAuthority()
    {
        return authority;
    }

    public void setAuthority( String authority )
    {
        this.authority = authority;
    }
}
