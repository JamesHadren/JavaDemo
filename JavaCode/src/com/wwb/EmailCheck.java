package com.wwb;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.regex.Pattern;

public class EmailCheck {
    final Pattern emailPattern =Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    final Function<String,Boolean> emailChecker =s->emailPattern.matcher(s).matches();

     public    void testMail(String email){
        if(emailChecker.apply(email)){
            sendVerificationMail(email);
        }
        else{
            logError("email "+email+"is invalid.");
        }
    }



    public void sendVerificationMail(String email){
        System.out.println("Verification mail sent to "+email);
    }
    public void logError(String s){
        System.out.println("Error message logged:"+s);
    }
    public static  void main(String[] args){
        EmailCheck ec=new EmailCheck();
        ec.testMail("jo_%+-h.n.doe@qcme.com");
        ec.testMail(null);
        ec.testMail("paul.smith@acme.com");

    }
}
