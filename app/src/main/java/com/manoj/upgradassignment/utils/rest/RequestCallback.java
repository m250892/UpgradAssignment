package com.manoj.upgradassignment.utils.rest;

public interface RequestCallback<T> {
    void onRequestSuccess(T t);

    void onRequestError(String error);
}