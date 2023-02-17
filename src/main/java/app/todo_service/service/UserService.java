package app.todo_service.service;

import app.todo_service.entities.LoginRequest;
import app.todo_service.entities.User;
import app.todo_service.repositories.UserRepo;
import app.todo_service.security.JwtTokenUtil;
import app.todo_service.security.JwtUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class UserService {

    @Autowired
    JwtTokenUtil  jwtTokenUtil;
    @Autowired
    UserRepo userRepo;

    public String login(String username, String password) {


        String token =null;
        try {

            User usr =userRepo.findByUsernameAndPassword(username,password);
            System.out.println("usr : "+ usr +", username:"+username +", pass:"+ password+"#");
            if(usr!=null && usr.getPassword().equals(password)){

                String role = "admin";
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
                UserDetails userDetails = new JwtUser("1", authorities, usr);
// Issue a token
                token = jwtTokenUtil.generateToken(userDetails);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        } catch (NoSuchElementException e) {
        }

        return token;
    }


    public User inscription(String username, String password) {
         User user=new User();
             user.setUsername(username);
             user.setPassword(password);

      return  userRepo.save(user);
    }
}
