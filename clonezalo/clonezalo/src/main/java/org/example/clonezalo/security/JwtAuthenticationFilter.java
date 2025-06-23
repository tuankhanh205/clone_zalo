package org.example.clonezalo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.clonezalo.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 🔍 Lấy Authorization header
        final String authHeader = request.getHeader("Authorization");

        // ❌ Nếu không có Bearer token thì bỏ qua
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✂️ Lấy token
        final String jwt = authHeader.substring(7);
        final String userPhone = jwtService.extractUsername(jwt); // bạn cần có extractUsername trong JwtService
        System.out.println("==> Token nhận được: " + jwt);
        System.out.println("==> User từ token: " + userPhone);
        System.out.println("==> Xác thực hiện tại: " + SecurityContextHolder.getContext().getAuthentication());

        System.out.println("==> Phone từ token: " + userPhone);
        System.out.println("==> Tồn tại trong DB: " + userRepository.findByPhone(userPhone).isPresent());

        // ✅ Nếu có user và chưa đăng nhập
        if (userPhone != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userRepository.findByPhone(userPhone)
                    .orElse(null);

            if (userDetails != null && jwtService.isTokenValid(jwt, userDetails)) {
                // Tạo token xác thực
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 🔐 Gắn vào SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("==> Xác thực hiện tại: " + SecurityContextHolder.getContext().getAuthentication());

            }
        }

        // 🚪 Tiếp tục filter
        filterChain.doFilter(request, response);
    }
}
