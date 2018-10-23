package designPattern.StrategyPattern;

public class GreenHeadDurk extends Durk{

    public GreenHeadDurk(){
        flyBehavior = new BadFly();
    }


}
