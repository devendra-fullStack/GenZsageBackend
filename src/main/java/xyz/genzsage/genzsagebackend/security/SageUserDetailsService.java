package xyz.genzsage.genzsagebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.genzsage.genzsagebackend.model.Sage;
import xyz.genzsage.genzsagebackend.repository.SageRepository;



@Component
public class SageUserDetailsService implements UserDetailsService {
    private final SageRepository repo;

    @Autowired
    public SageUserDetailsService(SageRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Sage s = repo.findByIdentity(username)
                .orElseThrow(() -> new UsernameNotFoundException("Sage not found: " + username));

        // If you have roles on Sage, map them here. For now default to ROLE_USER.
        return User.withUsername(s.getIdentity())
                .password(s.getPasswordHash())
                .roles("USER")
                .build();    }
}
