package com.wwb;

import java.util.regex.Pattern;

public class EmailValidation3 {
    static Pattern emailPattern =Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static  Function<String,Result1<String>> emailChecker=s->{
        Result1 result = s == null
                ?  Result1.failure("email must not be null")
                : s.length() == 0
                ?  Result1.failure("email must not be empty")
                : emailPattern.matcher(s).matches()
                ?  Result1.success(s)
                :  Result1.failure("email " + s + "  is invalid");
        return result;
    };

    public static  void main(String ... args){
        emailChecker.apply("this.is@my.email").bind(success,failure);
        emailChecker.apply(null).bind(success,failure);
        emailChecker.apply("").bind(success,failure);
        emailChecker.apply("john.doe@acme.com").bind(success,failure);
    }

    static  Effect<String> success =s->System.out.println("mail sent to "+s);
    static  Effect<String> failure =s->System.out.println("Error message logged "+s);
}

