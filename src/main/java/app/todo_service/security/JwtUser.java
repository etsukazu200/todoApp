package app.todo_service.security;


import app.todo_service.entities.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JwtUser implements  UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = -2064537754337002673L;

  private final  String id;

  private final  String username;
  private final  String password;

  private  final Collection<? extends GrantedAuthority> authorities;

  //private final  UserRights rights;

  //private final  String group;

    public JwtUser(String sessionId,List<GrantedAuthority> authorities, User usr) {

        this.id = sessionId;
        this.authorities = authorities;
        this.username = usr.getUsername();
        this.password =  usr.getPassword();


        Date lastPwdResetDateTmp = null;
        //this.lastPwdResetDate = (lastPwdResetDateTmp != null) ? lastPwdResetDateTmp : created;

    }
  public JwtUser(String sessionId, List<GrantedAuthority> authorities, Map<String,String> userJSONData) {
    this.id = sessionId;
    this.authorities = authorities;
    //this.group = group;
    this.username = userJSONData.get("username");
    this.password =  userJSONData.get("password");

  }


  private JwtUser(JwtUserBuilder jwtUserBuilder) {
    this.id = jwtUserBuilder.id;
    this.authorities = jwtUserBuilder.authorities;
    this.username = jwtUserBuilder.username;
    this.password =  jwtUserBuilder.password;

  }

public static class JwtUserBuilder {

    private  String id;
    private  String username;
    private  String firstName;
    private  String lastName ;
    private  String password;
    private  String email ;
    private  Collection<? extends GrantedAuthority> authorities;
    private  Date created;
    private Date validityStartDate;
    private Date validityEndDate;
    private Date lastPwdResetDate;

    public JwtUserBuilder (String username, String password, String id){
      this.username = username;
      this.password =  password;
      this.id = id;
    }

    public JwtUserBuilder firstName(String firstName)
    {
      this.firstName = firstName;
      return this;
    }

    public JwtUserBuilder lastName(String lastName)
    {
      this.lastName = lastName;
      return this;
    }


    public JwtUserBuilder email(String email)
    {
      this.email = email;
      return this;
    }

    public JwtUserBuilder authorities(Collection<? extends GrantedAuthority> authorities )
    {
      this.authorities = authorities;
      return this;
    }

    public JwtUserBuilder created(Date created )
    {
      this.created = created;
      return this;
    }


    public JwtUserBuilder validityStartDate(Date validityStartDate)
    {
      this.validityStartDate = validityStartDate;
      return this;
    }

    public JwtUserBuilder validityEndDate(Date validityEndDate)
    {
      this.validityEndDate = validityEndDate;
      return this;
    }

    public JwtUserBuilder lastPwdResetDate(Date lastPwdResetDate)
    {
      this.lastPwdResetDate = lastPwdResetDate;
      return this;
    }



    public JwtUser build ()
    {
      return new JwtUser(this);
    }

  }
  
  

  public static JwtUser getPrincipal() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    JwtUser user = (JwtUser)auth.getPrincipal();
    return user;
  }



  @JsonIgnore
  public  String getId() {
    return id;
  }

  public  String getUsername() {
    return username;
  }

  @JsonIgnore
  public  boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  public  boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  public  boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  public  String getPassword() {
    return password;
  }


  @Override
  public boolean isEnabled() {
    return true;
  }


  public  Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


}
