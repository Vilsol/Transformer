package me.vilsol.transformer.engine.algorithms;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.handlers.TransformerHandler;

public enum AlgorithmType {

    REPLACE(new ReplaceAlgorithm(null)),
    OVERLAY(new OverlayAlgorithm(null));

    private TransformerAlgorithm algorithm;

    AlgorithmType(TransformerAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void newInstance(TransformerHandler handler, ParamCallback<TransformerAlgorithm> callback){
        algorithm.newInstance(handler, callback);
    }

    public TransformerAlgorithm getExample() {
        return algorithm;
    }

}
