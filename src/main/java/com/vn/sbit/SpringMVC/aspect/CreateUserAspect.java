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

//Trong Spring AOP, @Around là một annotation mạnh mẽ và linh hoạt hơn, bao gồm cả @Before, @After, @AfterReturning, và @AfterThrowing. Annotation
// @Around cho phép bạn can thiệp vào quá trình thực thi của một phương thức trước khi phương thức đó bắt đầu và sau khi nó hoàn thành.
//@Around("execution(* com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl.createUser(..))")
//public Object aroundCreateUser(ProceedingJoinPoint joinPoint) throws Throwable {
//    // Trước khi phương thức thực thi
//    System.out.println("Before method execution");
//
//    try {
//        Object result = joinPoint.proceed(); // Thực thi phương thức
//
//        // Sau khi phương thức hoàn thành mà không có ngoại lệ
//        System.out.println("After method execution");
//        return result;
//    } catch (Throwable throwable) {
//        // Xử lý ngoại lệ nếu có
//        System.out.println("After throwing exception");
//        throw throwable;
//


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
