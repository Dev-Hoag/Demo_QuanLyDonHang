package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.RegisterRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.LoginRequest;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {

    private final String SUPABASE_URL = "https://jdogabuoifwjfvblnnld.supabase.co";
    private final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Impkb2dhYnVvaWZ3amZ2YmxubmxkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTMwNzYxMDIsImV4cCI6MjA2ODY1MjEwMn0.kGLiR2cWZ6-cqRL9fOR00Qu_AbBxsrT-AU0oL8Luz8A";
    private final String SUPABASE_SERVICE_ROLE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Impkb2dhYnVvaWZ3amZ2YmxubmxkIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc1MzA3NjEwMiwiZXhwIjoyMDY4NjUyMTAyfQ.ZsFnD3fRp6w-dKUNObJpFuQmbHR7i_fHJnudZM41BIk";

    private final OkHttpClient client = new OkHttpClient();

   private void insertUserToDatabase(String userId, RegisterRequest request) {
    JSONObject json = new JSONObject();
    json.put("id", userId);
    json.put("email", request.getEmail());
    json.put("phone", request.getPhone());
    json.put("name", request.getName());

    // Nếu request có role thì dùng, không thì mặc định user
    String role = (request.getRole() != null && !request.getRole().isEmpty())
            ? request.getRole()
            : "user";
    json.put("role", role);

    RequestBody body = RequestBody.create(
            json.toString(),
            MediaType.parse("application/json")
    );

    Request dbRequest = new Request.Builder()
            .url(SUPABASE_URL + "/rest/v1/users")
            .addHeader("apikey", SUPABASE_API_KEY)
            .addHeader("Authorization", "Bearer " + SUPABASE_SERVICE_ROLE_KEY)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

    try (Response response = client.newCall(dbRequest).execute()) {
        if (!response.isSuccessful()) {
            System.err.println("❌ Insert DB failed: " + response.code() + " - " + response.body().string());
        } else {
            System.out.println("✅ Inserted user into DB successfully with role: " + role);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public String register(RegisterRequest request) {
    JSONObject payload = new JSONObject();
    payload.put("email", request.getEmail());
    payload.put("password", request.getPassword());
    payload.put("email_confirm", true);

    // Lưu role vào user_metadata để JWT có role
    if (request.getRole() != null && !request.getRole().isEmpty()) {
        JSONObject userMetadata = new JSONObject();
        userMetadata.put("role", request.getRole());
        payload.put("user_metadata", userMetadata);
    }

    RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));

    Request req = new Request.Builder()
            .url(SUPABASE_URL + "/auth/v1/admin/users")
            .addHeader("apikey", SUPABASE_API_KEY)
            .addHeader("Authorization", "Bearer " + SUPABASE_SERVICE_ROLE_KEY)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

    try (Response response = client.newCall(req).execute()) {
        String responseBody = response.body().string();

        if (!response.isSuccessful()) {
            System.err.println("❌ Admin Create User failed: " + responseBody);
            return responseBody;
        }

        JSONObject json = new JSONObject(responseBody);
        if (!json.has("id")) {
            return "❗ Không lấy được user ID sau khi tạo.";
        }

        String userId = json.getString("id");

        // Lưu role vào bảng users
        insertUserToDatabase(userId, request);

        return "✅ Tạo user thành công!";
    } catch (IOException e) {
        e.printStackTrace();
        return "❌ Lỗi: " + e.getMessage();
    }
}


    public String login(LoginRequest request) {
        JSONObject payload = new JSONObject();
        payload.put("email", request.getEmail());
        payload.put("password", request.getPassword());

        RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));

        Request req = new Request.Builder()
                .url(SUPABASE_URL + "/auth/v1/token?grant_type=password")
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(req).execute()) {
            if (response.body() == null) {
                return "❌ Không có phản hồi từ Supabase.";
            }

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "❌ Lỗi: " + e.getMessage();
        }
    }
}
