package com.wwb;

import java.util.*;

public class CollectionUtilities {
    public static  <T> List<T> list(){
        return Collections.emptyList();
    }

    public static  <T> List<T> list(List<T> ts){
        return  Collections.unmodifiableList(new ArrayList<>(ts));
    }

    @SafeVarargs
    public static  <T> List<T> list(T... t){
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t,t.length)));
    }

}
