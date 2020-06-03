package com.mhmdawad.newsapp.ui.main;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainResource<T> {

    @NonNull
    public final MainStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public MainResource(@NonNull MainStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> MainResource<T> loaded (@Nullable T data) {
        return new MainResource<>(MainStatus.LOADED, data, null);
    }

    public static <T> MainResource<T> error(@NonNull String msg, @Nullable T data) {
        return new MainResource<>(MainStatus.ERROR, data, msg);
    }

    public static <T> MainResource<T> loading(@Nullable T data) {
        return new MainResource<>(MainStatus.LOADING, data, null);
    }


    public enum MainStatus { ERROR, LOADING, LOADED }

}
