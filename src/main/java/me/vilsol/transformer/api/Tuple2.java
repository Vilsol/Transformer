package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public class Tuple2<A, B> implements Cloneable {

    private final A a;
    private final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || !(o instanceof Tuple2))
            return false;
        Tuple2<?, ?> tuple = (Tuple2<?, ?>) o;
        return (a == tuple.getA() || a.equals(tuple.getA())) && (b == tuple.getB() || b.equals(tuple.getB()));
    }

    public <C> Tuple3<A, B, C> toTuple3(C c) {
        return new Tuple3<>(a, b, c);
    }

    @Override
    public Tuple2<A, B> clone() {
        return new Tuple2<>(a, b);
    }

    @SuppressWarnings("unchecked")
    public Tuple2<A, B> deepClone() {
        A a = this.a;
        B b = this.b;

        try {
            a = (A) a.getClass().getMethod("clone").invoke(a);
        } catch (Exception ignored) {
        }
        try {
            b = (B) b.getClass().getMethod("clone").invoke(b);
        } catch (Exception ignored) {
        }

        return new Tuple2<A, B>(a, b);
    }

}
