package com.renovavision.cleanmvp.util.holder;

import com.renovavision.cleanmvp.presenters.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class PresenterHolder {

    private final Map<Class, BasePresenter> presenterMap;

    public PresenterHolder() {
        this.presenterMap = new HashMap<>();
    }

    public void putPresenter(Class c, BasePresenter p) {
        presenterMap.put(c, p);
    }

    public <T extends BasePresenter> T getPresenter(Class<T> c) {
        return (T) presenterMap.get(c);
    }

    public void remove(Class c) {
        presenterMap.remove(c);
    }
}
