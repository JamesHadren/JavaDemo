package com.wwb;

import java.util.function.Function;

public class Demo1 {
    public static  void main(String[] args){

        Function2<Double,Double,Double>  addTax=(taxRate,price)->price+price*taxRate;
        double priceIncludingTax =addTax.apply(0.09,12.0);
        System.out.println(priceIncludingTax);

        double tax=0.09;
        Function<Double,Function<Double,Double>> addTax1=taxRate->price->price+price*taxRate;
        System.out.println(addTax1.apply(tax).apply(12.0));
    }
}
