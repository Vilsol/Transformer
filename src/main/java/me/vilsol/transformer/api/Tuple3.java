package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public class Tuple3<A, B, C> implements Cloneable {

    private final A a;
    private final B b;
    private final C c;

    public Tuple3(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || !(o instanceof Tuple3))
            return false;
        Tuple3<?, ?, ?> tuple = (Tuple3<?, ?, ?>) o;
        return (a == tuple.getA() || a.equals(tuple.getA())) && (b == tuple.getB() || b.equals(tuple.getB()))
                && (c == tuple.getC() || c.equals(tuple.getC()));
    }

    public Tuple2<A, B> toTuple2() {
        return new Tuple2<>(a, b);
    }

    @Override
    public Tuple3<A, B, C> clone() {
        return new Tuple3<>(a, b, c);
    }

    @SuppressWarnings("unchecked")
    public Tuple3<A, B, C> deepClone() {
        A a = this.a;
        B b = this.b;
        C c = this.c;

        try {
            a = (A) a.getClass().getMethod("clone").invoke(a);
        } catch (Exception ignored) {
        }
        try {
            b = (B) b.getClass().getMethod("clone").invoke(b);
        } catch (Exception ignored) {
        }
        try {
            c = (C) c.getClass().getMethod("clone").invoke(c);
        } catch (Exception ignored) {
        }

        return new Tuple3<>(a, b, c);
    }

}
