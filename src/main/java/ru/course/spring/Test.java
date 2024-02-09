package ru.course.spring;

public class Test {

    public static void main(String[] args) {
        System.out.println(fizzBizz(3));
        System.out.println( fizzBizz(5));
        System.out.println(fizzBizz(15));
        System.out.println(fizzBizz(11));

    }
    public static  byte[] fizzBizz(int num){

        if (num %3 == 0 && num %5 == 0){
            return "fizzBizz".getBytes();

        }else if(num % 5 == 0){
            return "bizz".getBytes();
        }else if(num %3 == 0){
            return "fizz".getBytes();
        }else{
            throw new IllegalArgumentException();
        }

    }
}
