package com.project2.wanderfun.infrastructure.filter;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.infrastructure.security.CustomUserDetails;
import com.project2.wanderfun.infrastructure.security.UserDetailServiceImpl;
import com.project2.wanderfun.presentation.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.internal.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailServiceImpl userDetailService;
    private final ObjectMapper objectMapper;

    public JwtFilter(JwtUtil jwtUtil, UserDetailServiceImpl userDetailService, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws UnauthorizedException, IOException{
        try {
            if (isPublicApi(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            final String token = header.substring(7);
            if(jwtUtil.validateToken(token) != true) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            String email = jwtUtil.getEmailFromToken(token);
            String role = jwtUtil.getRoleFromToken(token);
            if (email != null && role != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                CustomUserDetails userDetails = userDetailService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

//            if(role == null && !isRefreshApi(request)) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                return;
//            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isPublicApi(HttpServletRequest request) {
        final List<Pair<String, String>> publicApis = Arrays.asList(
                Pair.of("POST", "/wanderfun/auth/register"),
                Pair.of("POST", "/wanderfun/auth/login"),
                Pair.of("POST", "/wanderfun/auth/admin/register"),

                Pair.of("GET", "/wanderfun/place"),

                // Swagger endpoints
                Pair.of("GET", "/swagger-ui"),
                Pair.of("GET", "/v3/api-docs")
        );

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();

        return publicApis.stream()
                .anyMatch(api -> api.getLeft().equalsIgnoreCase(requestMethod)
                        && requestPath.startsWith(api.getRight()));
    }

    private boolean isRefreshApi(HttpServletRequest request) {
        final Pair<String, String> refreshApi = Pair.of("GET", "/wanderfun/auth/refresh");

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();

        return refreshApi.getLeft().equalsIgnoreCase(requestMethod)
                && requestPath.startsWith(refreshApi.getRight());
    }
}
