package com.example.app.Modelos;

import androidx.annotation.NonNull;

public enum TipoFichaje implements CharSequence {
    ENTRADA,
    SALIDA;

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @NonNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
