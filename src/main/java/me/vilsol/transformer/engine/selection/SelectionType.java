package me.vilsol.transformer.engine.selection;

public enum SelectionType {

    ONE_POINT(OnePointSelection.class) {
        @Override
        public Selection newInstance() {
            return new OnePointSelection(null);
        }
    },

    TWO_POINTS(TwoPointSelection.class){
        @Override
        public Selection newInstance() {
            return new TwoPointSelection(null, null);
        }
    };

    private Class<? extends Selection> clazz;

    SelectionType(Class<? extends Selection> clazz){
        this.clazz = clazz;
    }

    public boolean equalClass(Selection selection){
        return selection != null && clazz.isAssignableFrom(selection.getClass());
    }

    public Selection newInstance(){
        return null;
    }

}
