package com.vn.sbit.SpringMVC.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CreateUserAspect {
    /* * là đại diện cho tất cả access modify ( default,private,protected , public , static )
    *  () là đại diện cho method (no parameter ); . ( sẽ phải thêm vào 1 parameter tương ứng với type của method); .. sẽ là all parameter
    * */
    @Before("execution (* com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl.createUser(..) )")
    public void beforeAdd(JoinPoint joinPoint){
        System.out.println("Ready create user");
    }

    // co the goi pointcut (name method) vd @After("printResult()")
    @After("execution (* com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl.createUser(..) )")
    public void afterAdd(JoinPoint joinPoint){
        System.out.println("Create user");
    }

    @AfterThrowing("execution (* com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl.createUser(..) )")
    public void exceptionAdd(JoinPoint joinPoint){
        System.out.println("Error create user");
    }

//    @Around()

    // diem ta thuc thi
    @Pointcut("execution (* com.vn.sbit.SpringMVC.service.Impl.(.*.*) )")
    public void printResult(){
    }

    //tai su dung lai execution
    @After("printResult()")
    public void afterOperation(JoinPoint joinPoint){
        System.out.println("Impl");
    }



    

}
