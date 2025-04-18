package vn.iotstar.baitaptuan7_vidukhac.Volley;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";

    // Constructor riêng tư
    private SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // Lấy instance duy nhất
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    // Lưu thông tin user khi đăng nhập
    public void userLogin(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_NAME, user.getName());
        editor.apply();
    }

    // Kiểm tra xem đã đăng nhập chưa
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Lấy thông tin user
    public User getUser() {
        int id = prefs.getInt(KEY_ID, 0);
        String email = prefs.getString(KEY_EMAIL, null);
        String name = prefs.getString(KEY_NAME, null);
        return new User(id, email, name);
    }

    // Đăng xuất
    public void logout() {
        editor.clear();
        editor.apply();
    }
}