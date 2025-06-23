package org.example.clonezalo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Service
public class JwtService {
    @Value("${secret.key}")
    private String secretKey;
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        System.out.println("Độ dài khóa: " + keyBytes.length * 8 + " bit");
        return Keys.hmacShaKeyFor(keyBytes);

    }

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 tiếng

    public String generateAccessToken(String phone,String role) {
        // In ra secretKey đang dùng khi tạo token và khi xác minh
        System.out.println("Secret Key: " + secretKey);
        return Jwts.builder().setSubject(phone).setIssuedAt(new Date())
                .claim("role",role)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    public String generateRefreshToken(String phone) {
        return Jwts.builder()
                .setSubject(phone)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 ngày
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Trích xuất username (ở đây là phone)
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Kiểm tra token có hợp lệ không
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }


}
