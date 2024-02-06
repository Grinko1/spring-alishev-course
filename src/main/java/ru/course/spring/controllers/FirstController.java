package ru.course.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

//        System.out.println(name + " " + surname);
        model.addAttribute("message" ,name + " " + surname);

        return "/first/hello_word";
    }

    //    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request){
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.println(name+" "+surname);
//
//        return "/first/hello_word";
//    }
    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }
    @GetMapping("calculator")
    public String calculator(@RequestParam(value = "a") int a,
                           @RequestParam(value = "b") int b,
                           @RequestParam(value = "action") String action,
                           Model model){
        System.out.println("a "+a +" "+"b "+b +" action "+ action);
        double res =0;
        switch(action){
            case "multiplication":
                res = a *b;
                break;
            case "addition":
                res = a + b;
                break;
            case "subtraction":
                res = a-b;
                break;
            case "division":
                if(b != 0){
                    res =  a /(double) b;

                }else{
                    System.out.println("Divide by zero");
                }

                break;
            default:
                System.out.println("Wrong data");;
        }
        model.addAttribute("result", res);
        return "/first/calculator";

    }
    }
