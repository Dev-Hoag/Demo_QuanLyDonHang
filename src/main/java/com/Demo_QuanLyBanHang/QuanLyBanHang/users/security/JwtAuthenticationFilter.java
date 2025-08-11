package com.Demo_QuanLyBanHang.QuanLyBanHang.users.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY =
            "SDKyICeIJKBneSC9Oi2ltqBQUmw0Eg4IL2aa+THiXE/s1Nzq3xBKPpPzp2CInGR778BRp5xW8osKzD4/L4McdQ==";

    private static final String SUPABASE_URL = "https://jdogabuoifwjfvblnnld.supabase.co";
    private static final String SUPABASE_SERVICE_ROLE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Impkb2dhYnVvaWZ3amZ2YmxubmxkIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc1MzA3NjEwMiwiZXhwIjoyMDY4NjUyMTAyfQ.ZsFnD3fRp6w-dKUNObJpFuQmbHR7i_fHJnudZM41BIk";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String roleFromDb = getRoleFromSupabase(userId);
            if (roleFromDb == null) {
                roleFromDb = "USER";
            }

            var authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + roleFromDb.toUpperCase())
            );

            var authToken = new UsernamePasswordAuthenticationToken(userId, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception ignored) {
        }

        filterChain.doFilter(request, response);
    }

    private String getRoleFromSupabase(String userId) {
        try {
            String url = SUPABASE_URL + "/rest/v1/users?select=role&id=eq." + userId;

            Request req = new Request.Builder()
                    .url(url)
                    .addHeader("apikey", SUPABASE_SERVICE_ROLE_KEY)
                    .addHeader("Authorization", "Bearer " + SUPABASE_SERVICE_ROLE_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response resp = client.newCall(req).execute()) {
                if (!resp.isSuccessful()) {
                    return null;
                }
                String body = resp.body().string();
                JSONArray arr = new JSONArray(body);
                if (arr.length() > 0) {
                    JSONObject obj = arr.getJSONObject(0);
                    return obj.optString("role", null);
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
