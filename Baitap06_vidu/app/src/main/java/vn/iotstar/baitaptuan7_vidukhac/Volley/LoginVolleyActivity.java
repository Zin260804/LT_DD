package vn.iotstar.baitaptuan7_vidukhac.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import vn.iotstar.baitaptuan7_vidukhac.R;

public class LoginVolleyActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_volley);

        // Kiểm tra nếu đã đăng nhập, chuyển thẳng sang Profile
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }

        // Ánh xạ giao diện
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Sự kiện nhấn nút Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        final String email = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo JSON object để gửi lên server
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Tạo request bằng Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.URL_LOGIN, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Kiểm tra phản hồi từ server
                            if (response.getBoolean("error")) {
                                Toast.makeText(LoginVolleyActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                // Lấy thông tin user từ JSON
                                JSONObject userJson = response.getJSONObject("user");
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("email"),
                                        userJson.getString("name")
                                );
                                // Lưu thông tin user và chuyển sang Profile
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                startActivity(new Intent(LoginVolleyActivity.this, ProfileActivity.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginVolleyActivity.this, "Lỗi kết nối: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Thêm request vào queue
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}