package fr.lri.kn.utils;
public interface Observer<T> {
    void notify(T data);
}
