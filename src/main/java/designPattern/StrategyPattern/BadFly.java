package designPattern.StrategyPattern;

public class BadFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.print("----bad fly----");
    }
}
