package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
        public static void main(String[] args) {
            Thread t1=new Thread(new Runnable(){
                @Override
                public void run() {
                    for(int i=1;i<100;i+=2)
                        System.out.println("ogg:"+i);
                }
            });
            Thread t2=new Thread(new Runnable(){
                @Override
                public void run() {
                    for(int i=0;i<100;i+=2)
                        System.out.println("even:"+i);
                }
            });
//            t1.start();
//            t2.start();
//            try {
//                t1.join();
//                t2.join();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.out.println("end");

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(t1);
            executor.submit(t2);
            executor.shutdown();
        }
}
