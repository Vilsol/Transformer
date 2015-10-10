package me.vilsol.transformer.api;

public interface TProvider<T> {

    String getName();

    T getOwner();

    TLocation getA();

    void setA(TLocation a);

    TLocation getB();

    void setB(TLocation b);

}
