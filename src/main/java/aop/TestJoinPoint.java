package aop;

public class TestJoinPoint {


    @MyInfoAnnotation
    public String testAop(){
        return "------";
    }
}
