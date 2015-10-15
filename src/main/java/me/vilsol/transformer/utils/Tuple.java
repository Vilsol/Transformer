package me.vilsol.transformer.utils;

public class Tuple<T, M> {

    private T a;
    private T b;

    public Tuple(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    @Override
    public int hashCode() {
        return a.hashCode() * b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple && ((Tuple) obj).getA().equals(a) && ((Tuple) obj).getB().equals(b);

    }

    @Override
    public String toString() {
        return "Tuple{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

}
