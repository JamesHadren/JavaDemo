package com.wwb;

public abstract  class TailCall<T> {
    public abstract  TailCall<T> resume();
    public abstract  T eval();
    public abstract  boolean isSupspend();

    private TailCall(){}

    private static  class Return<T> extends  TailCall<T>{
        private final  T t;
        private Return(T t){
            this.t=t;
        }

        @Override
        public TailCall<T> resume() {
            throw new IllegalStateException("return has no  resume");
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSupspend() {
            return false;
        }
    }

    private static  class Suspend<T> extends   TailCall<T>{
        private final  Supplier<TailCall<T>> resume;
        private Suspend(Supplier<TailCall<T>> resume){
            this.resume=resume;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }

        @Override
        public T eval() {
            TailCall<T> tailCall =this;
            while(tailCall.isSupspend()) {
                tailCall=tailCall.resume();
            }
            return tailCall.eval();
        }

        @Override
        public boolean isSupspend() {
            return true;
        }
    }

    public static  <T> Return<T> ret(T t){
        return new Return<>(t);
    }
    public static  <T> Suspend<T> sus(Supplier<TailCall<T>> s){
        return new Suspend<>(s);
    }
}
