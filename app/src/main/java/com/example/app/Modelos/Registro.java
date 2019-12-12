package com.example.app.Modelos;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.Objects;

public class Registro implements Serializable {
    private static final long serailVersionUI = 1L;
    private String user;
    private String password;

    public Registro(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return password == registro.password &&
                Objects.equals(user, registro.user);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }
}
