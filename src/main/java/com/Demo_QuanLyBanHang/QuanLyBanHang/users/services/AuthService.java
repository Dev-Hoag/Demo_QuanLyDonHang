package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.RegisterRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.LoginRequest;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {

    private final String SUPABASE_URL = "https://jdogabuoifwjfvblnnld.supabase.co";
    private final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Impkb2dhYnVvaWZ3amZ2YmxubmxkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTMwNzYxMDIsImV4cCI6MjA2ODY1MjEwMn0.kGLiR2cWZ6-cqRL9fOR00Qu_AbBxsrT-AU0oL8Luz8A";

    private final OkHttpClient client = new OkHttpClient();

   public String register(RegisterRequest request) {
    String json = "{ \"email\": \"" + request.getEmail() + "\", " +
                  "\"password\": \"" + request.getPassword() + "\", " +
                  "\"data\": { \"phone\": \"" + request.getPhone() + "\" } }";

    RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

    Request req = new Request.Builder()
            .url(SUPABASE_URL + "/auth/v1/signup")
            .addHeader("apikey", SUPABASE_API_KEY)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

    try (Response response = client.newCall(req).execute()) {
        return response.body().string();
    } catch (IOException e) {
        e.printStackTrace();
        return "Error: " + e.getMessage();
    }
}


    public String login(LoginRequest request) {
        String json = "{ \"email\": \"" + request.getEmail() + "\",\"password\": \"" + request.getPassword() + "\" }";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request req = new Request.Builder()
                .url(SUPABASE_URL + "/auth/v1/token?grant_type=password")
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(req).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
