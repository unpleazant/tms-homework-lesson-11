package com.tms11;

import java.util.AbstractList;

public class MyList<T> extends AbstractList<T> {

    private final T[] a;

    MyList(T[] array) {
        a = array;
    }

    public T get(int index) {
        return a[index];
    }

    public T set(int index, T element) {
        T oldValue = a[index];
        a[index] = element;
        return oldValue;
    }

    public int size() {
        return a.length;
    }
}