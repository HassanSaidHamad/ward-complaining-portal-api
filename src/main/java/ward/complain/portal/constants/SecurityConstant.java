package ward.complain.portal.constants;

public class SecurityConstant {
    public static final String AUTHORITIES = "authorities";

    //    public static final long EXPIRATION_TIME = 432_000_000;     // 5 days expressed in milliseconds
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String JWT_TOKEN_HEADER = "Jwt-token";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String FORBIDDEN_MESSAGE = "You need to login to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String[] PUBLIC_SWAGGER_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-resources/**",
            "/webjars/**"
    };
    public static final String[] PUBLIC_URLS = {"/api/v1/auth/login", "/api/v2/users/register/**", "/api/v2/users/image/**",
            "/user/image/**", "/schools/image/**", "/api/v5/schools/image/**", "/api/v17/universities/image/**", "/api/v7/projects/download/**",
            "/api/v2/users/reset/password/**", "/api/v7/projects/attachment/**"};


}
