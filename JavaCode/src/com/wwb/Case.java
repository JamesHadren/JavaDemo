package com.wwb;
/*
   用case类匹配条件
 */
public class Case<T> extends  Tuple<Supplier<Boolean>,Supplier<Result1<T>>> {
    private Case(Supplier<Boolean> booleanSupplier,Supplier<Result1<T>> result1Supplier){
        super(booleanSupplier,result1Supplier);
    }

    public static  <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result1<T>> value){
        return new Case<>(condition,value);
    }


    public static  <T> DefaultCase<T> mcase(Supplier<Result1<T>> value){
        return new DefaultCase<>(()->true,value);
    }
    private static  class DefaultCase<T> extends  Case<T>{
        private DefaultCase(Supplier<Boolean> booleanSupplier,Supplier<Result1<T>> result1Supplier){
            super(booleanSupplier,result1Supplier);
        }

    }

    @SafeVarargs
    public static  <T> Result1<T>  match(DefaultCase<T> defaultCase,Case<T>... matchers){
        for(Case<T> aCase:matchers){
            if(aCase._1.get()) return aCase._2.get();
        }
        return defaultCase._2.get();
    }
}
