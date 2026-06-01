package ward.complain.portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ward.complain.portal.models.HttpResponse;

import java.io.IOException;

import static ward.complain.portal.constants.SecurityConstant.ACCESS_DENIED_MESSAGE;


@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exception) throws IOException {

        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value(),    //  403,
                HttpStatus.FORBIDDEN.getReasonPhrase(), // "FORBIDDEN",
                ACCESS_DENIED_MESSAGE    //   "You do not have permission to access this resource"
        );

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        new ObjectMapper().writeValue(response.getOutputStream(), httpResponse);
    }
}
