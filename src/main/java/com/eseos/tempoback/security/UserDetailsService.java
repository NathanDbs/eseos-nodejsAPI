package com.eseos.tempoback.security;

import com.eseos.tempoback.repository.UserRepository;
import com.eseos.tempoback.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        String testemail = "nathan.dubois@reseau.eseo.fr";
        log.debug("Authenticating {}", testemail);

        User user = userRepository.findUserByEmail(testemail).orElse(null);


        if (user == null) {
            log.debug("USER non trouvé en base");
            throw new UsernameNotFoundException("User " + testemail + " was not found in the database");
        }else{
            log.debug("user trouvé en base ! YOUPI !");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getGrade().getGrade());
        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                grantedAuthorities);
    }
}
