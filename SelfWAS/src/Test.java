public class Test {
    public static void main(String[] args) {
        String str = "abacddd";
        String[] tt = str.split("a");
        for(int i=0; i<tt.length; i++){
            System.out.println(i+":"+tt[i]);
        }
    }
}
