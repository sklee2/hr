package com.hyulib.login;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hyulib.login.domain.PortalLogin;
import com.hyulib.login.portaluser.Converter;
import com.hyulib.login.portaluser.PortalLoginOxmConfig;
import com.hyulib.login.portaluser.PortalLoginRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HYU on 2015-09-19.
 */
public class PortalLoginAuthenticationManager implements AuthenticationManager {
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
	private AnnotationConfigApplicationContext ctx;

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));

    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	ctx = new AnnotationConfigApplicationContext();
        ctx.register(PortalLoginOxmConfig.class);
        ctx.refresh();
        Converter converter = ctx.getBean(Converter.class);
        
        PortalLoginRequest portalSsoRequest = new PortalLoginRequest();

        InputStream inputStream = portalSsoRequest.portalLogin(auth.getName(), auth.getCredentials().toString());
        PortalLogin ResponseObject = null;

        try {
			ResponseObject = (PortalLogin) converter.doUnMarshaling(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        System.out.println(ResponseObject.getData().getGaeinNo());

        if (ResponseObject.getCode().equals("200") || ResponseObject.getCode().equals("504") ) {
            return new UsernamePasswordAuthenticationToken(ResponseObject.getData().getGaeinNo(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}