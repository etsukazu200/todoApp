package app.todo_service.Controller;

import app.todo_service.entities.LoginRequest;
import app.todo_service.entities.ResponseToken;
import app.todo_service.entities.User;
import app.todo_service.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseToken>authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate user

        String token=userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        ResponseToken responseToken=new ResponseToken(token);

      return   ResponseEntity.ok().body(responseToken);


    }
    @PostMapping("/inscription")
    public ResponseEntity<User>inscriptionUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate user

       User user=userService.inscription(loginRequest.getUsername(), loginRequest.getPassword());

        return   ResponseEntity.ok().body(user);


    }
}