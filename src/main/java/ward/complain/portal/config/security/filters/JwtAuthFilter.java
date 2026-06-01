package ward.complain.portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ward.complain.portal.config.security.CustomUserDetailsService;
import ward.complain.portal.config.utils.JwtUtils;
import ward.complain.portal.models.HttpResponse;

import java.io.IOException;

import static ward.complain.portal.constants.SecurityConstant.AUTHORIZATION;
import static ward.complain.portal.constants.SecurityConstant.TOKEN_PREFIX;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(AUTHORIZATION);
        String token = null;
        String userId = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            token = header.substring(7);

            try {
                userId = jwtUtils.getUserIdFromJwtToken(token).toString();

            } catch (ExpiredJwtException e) {
                sendExpiredTokenResponse(response);
                return; // STOP FILTER HERE

            } catch (JwtException e) {
                sendInvalidToken(response);
                return; // STOP FILTER
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserById(Long.parseLong(userId));

            if (jwtUtils.validateJwtToken(token)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendExpiredTokenResponse(HttpServletResponse response) throws IOException {
        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.value(),
//                "Unauthorized",
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "Token expired. Please log in again."
        );

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), httpResponse);
    }

    private void sendInvalidToken(HttpServletResponse response) throws IOException {
        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.value(),
//                "Unauthorized",
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "Invalid token."
        );

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), httpResponse);
    }
}
