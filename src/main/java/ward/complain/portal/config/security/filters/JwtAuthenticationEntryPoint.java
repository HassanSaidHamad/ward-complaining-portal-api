package ward.complain.portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import ward.complain.portal.models.HttpResponse;

import java.io.IOException;

import static ward.complain.portal.constants.SecurityConstant.FORBIDDEN_MESSAGE;


@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException {

        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.value(), // 401
                "UNAUTHORIZED",
                FORBIDDEN_MESSAGE   //  "You need to login to access this page"

        );

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), httpResponse);
    }
}
