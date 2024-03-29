package tezea.si.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * This configuration rejects all unauthenticated requests and sends error code
 * 401.
 * 
 * @author Nils Richard
 *
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler, Serializable {
    private static final long serialVersionUID = 1152669427419990320L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied");
	}

}
