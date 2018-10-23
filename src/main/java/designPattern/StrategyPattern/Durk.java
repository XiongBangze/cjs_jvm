package designPattern.StrategyPattern;

public abstract class Durk {

        public Durk(){}

        FlyBehavior flyBehavior;

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void fly(){
            flyBehavior.fly();
        }
}
