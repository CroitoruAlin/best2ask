package best2ask.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

public class UserCredentials extends User implements UserDetails {
    public UserCredentials() {
    }

    public UserCredentials(@NotEmpty @Size(min = 6, max = 30) String username, @NotEmpty @Size(min = 6, max = 30) String password) {
        super(username, password);
    }
    public UserCredentials(User user)
    {
        super(user);
    }
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setUsername(String username) {
        super.setUsername(username);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword()  {
       return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }


}
