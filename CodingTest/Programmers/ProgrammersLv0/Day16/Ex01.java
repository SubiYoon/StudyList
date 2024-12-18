package Algorithm.ProgrammersLv0.Day16;

//유한소수 판별
//a는 분자 b는 분모
public class Ex01 {
    public int solution(int a, int b) {
        int bottom = finalBottom(a, b);
        
        while(true){
            if(bottom%2==0){
                bottom/=2;
            }else if(bottom%5==0){
                bottom/=5;
            }else if(bottom==1){
                return 1;
            }else {
                return 2;
            }

        }
    }

    //기약분수 구하기(분모만)
    public int finalBottom(int a, int b){
        int bottom = 0;
        if(a>b){
            for(int i=2; i<=a; i++){
                if(a%i==0 && b%i==0){
                    a/=i;
                    b/=i;
                }
            }
        }else {
            for(int i=2; i<=b; i++){
                if(a%i==0 && b%i==0){
                    a/=i;
                    b/=i;
                }
            }
        }

        bottom=b;
        return bottom;
    }
}
