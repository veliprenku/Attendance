package com.example.attendance;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginTask extends AsyncTask<String, Void, Boolean> {
    private LoginTaskListener listener;

    public interface LoginTaskListener {
        void onLoginSuccess();
        void onLoginFailed();
    }

    public LoginTask(LoginTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... credentials) {
        if (credentials.length != 2) {
            return false; // Ensure we have both username and password.
        }
        String username = credentials[0];
        String password = credentials[1];

        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next(); // If the ResultSet has an entry, credentials are valid.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            listener.onLoginSuccess();
        } else {
            listener.onLoginFailed();
        }
    }
}
