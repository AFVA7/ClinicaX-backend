package co.edu.uniquindio.clinicaX.infra.security.model;

import co.edu.uniquindio.clinicaX.model.Administrador;
import co.edu.uniquindio.clinicaX.model.Cuenta;
import co.edu.uniquindio.clinicaX.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
    public class UserDetailsImpl implements UserDetails {

    private String username, password;
    private int codigo;
    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(Cuenta user){

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(user instanceof Paciente){
            authorities.add( new SimpleGrantedAuthority("PACIENTE") );
        }else if(user instanceof Administrador){
            authorities.add( new SimpleGrantedAuthority("ADMIN") );
        }else{
            authorities.add( new SimpleGrantedAuthority("MEDICO") );
        }
        return new UserDetailsImpl(user.getCorreo(), user.getPasswd(), user.getCodigo(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
