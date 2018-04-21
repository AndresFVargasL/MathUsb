package co.edu.usbcali.mathusb.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;

import co.edu.usbcali.mathusb.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.mathusb.utilities.Constantes;
import co.edu.usbcali.mathusb.utilities.FacesUtils;
import co.edu.usbcali.mathusb.utilities.SessionVariables;
import co.edu.usbcali.mathusb.utilities.Utilities;
import co.edu.usbcali.mathusb.utilities.SessionVariables.TIPO_CONTABILIZACION;

@Scope("singleton")
@Component("VortexAuthenticationProvider")
public class VortexAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Implementacion de la seguridad propia
	 */
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;

	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        String dominio = new Integer(1).toString();
        
        if(name == null || name.trim().equals("")){
        	throw new AuthenticationServiceException("Porfavor digite su usuario.");
        }
        
        if(password == null || password.trim().equals("")){
        	throw new AuthenticationServiceException("Porfavor digite su clave.");
        }

        UsuarioDTO usuarioDTO=null;
		try {
			usuarioDTO = LoginRestServiceClient.autenticar(name, password, dominio, businessDelegatorView.getParametroPorDescripcion(Constantes.URLREST).getValorParametro());
		
        if(usuarioDTO != null && usuarioDTO.getUsu_correo() != null){
	        // Metodo para verificar la contraseña  - obtenido de el managedbean Login en el metodo action_enter()
	        if (!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")){
	        	throw new AuthenticationServiceException("No se ha encontrado un usuario con las credenciales ingresadas");
			}
	        
        }else{
        	throw new AuthenticationServiceException("No se ha encontrado un usuario con las credenciales ingresadas");
        }
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new AuthenticationServiceException(e1.getMessage());
		}
		
		try {
			if (usuarioDTO != null 
					&& usuarioDTO.getUsu_login() != null
					&& usuarioDTO.getUsu_login().equalsIgnoreCase(name) == true
					&& usuarioDTO.getContrasenaMD5() != null
					&& usuarioDTO.getContrasenaMD5().trim().equals(Utilities.convertirMD5(password)) == true) {
				
				final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				grantedAuths.add(new SimpleGrantedAuthority("Admin"));

				final UserDetails principal = new User(name, password, grantedAuths);
				final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
				// Se suben en sesion la empresa y el ano fiscal
				FacesUtils.setManagedBeanInSession(SessionVariables.STRING_USUARIO, Integer.parseInt(usuarioDTO.getUsu_codigo()));
				FacesUtils.setManagedBeanInSession(SessionVariables.USUARIO_DTO, usuarioDTO);
				FacesUtils.setManagedBeanInSession(SessionVariables.STRING_EMPRESA_SESSION, new Integer(1));

				return auth;

			} else {
				throw new AuthenticationServiceException(
						"No se ha encontrado un usuario con las credenciales ingresadas");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationServiceException("No se ha encontrado un usuario con las credenciales ingresadas");
		}
	}

	/**
	 * Este metodo se llama primero cuando se autentica un usuario
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}