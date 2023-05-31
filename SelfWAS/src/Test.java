public class Test{
    public static void main(String[] args) {
        Thread t1 = new ThreadFirst();
        Thread t2 = new ThreadFirst();
        t1.start();
        t2.start();
    }
    static class ThreadFirst extends Thread{
        @Override
        public void run(){
            for(int i=0; i<100; i++){
                System.out.println(i);
            }
        }
    }
}