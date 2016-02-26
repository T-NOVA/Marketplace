package org.tnova.service.oauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.tnova.service.oauth.domain.Credentials;
import org.tnova.service.oauth.repository.CredentialsRepository;

public class JdbcUserDetailsService
    implements UserDetailsService {

        private static final Logger LOG = LoggerFactory.getLogger( JdbcUserDetailsService.class );

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credentials credentials = credentialsRepository.findByName(username);
        if(credentials == null) {
            throw new UsernameNotFoundException("User " + username + " not found in database.");
        }

        return new User(credentials.getName(),
            credentials.getPassword(), credentials.isEnabled(), true, true, true, credentials.getAuthorities());
    }
}
