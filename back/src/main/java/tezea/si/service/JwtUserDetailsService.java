package tezea.si.service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.UserTezea;

/**
 * The Spring Security Authentication Manager calls this method for getting the
 * user details from the database when authenticating the user details provided
 * by the user. It overrides the loadUserByUsername for fetching user details
 * from the database using the username.
 * 
 * @author Nils Richard
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserTezeaDAO userTezeaDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Just a temporary version with no db and with a single user possible
        // username : grogu
        // password : password (here it is hashed like if in db)
        // TODO call a real DAO class to access db
        if ("grogu".equals(username)) {
            return new User("grogu", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
