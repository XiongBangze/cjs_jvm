package asdas;

public class AppMain {

    public static void main(String[] args) {
        Sample test1 = new Sample("test1");
        Sample test2 = new Sample("test2");

        test1.printName();
        test2.printName();
    }

    static class Sample {

        private String name;

        public Sample(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void printName(){
            System.out.println(name);
        }
    }
}
