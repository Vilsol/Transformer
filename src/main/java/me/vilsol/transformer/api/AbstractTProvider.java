package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public abstract class AbstractTProvider<T> implements TProvider<T> {

    private T owner;
    private TLocation a, b;

    public AbstractTProvider(T owner) {
        this.owner = owner;
    }

    @Override
    public T getOwner() {
        return owner;
    }

    @Override
    public TLocation getA() {
        return a;
    }

    @Override
    public void setA(TLocation a) {
        this.a = a;
    }

    @Override
    public TLocation getB() {
        return b;
    }

    @Override
    public void setB(TLocation b) {
        this.b = b;
    }

}
