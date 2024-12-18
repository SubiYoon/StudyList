package Algorithm.ProgrammersLv2;

import java.util.Arrays;

/*
귤 고르기
k는 고를 귤의 갯수
tangerine은 귤의 크기의 종류를 나열한 것
1 ≤ k ≤ tangerine의 길이 ≤ 100,000
1 ≤ tangerine의 원소 ≤ 10,000,000
귤을 크기별로 분류했을 때 서로다른 크기의 귤의 종류가 최소가 되는 종류 수를 return
 */
public class ChooseTangerines {
    public int solution(int k, int[] tangerine) {
        int[] arr = new int[10000001];

        //귤배열의 숫자에 맞는 index에 ++
        for(int i=0; i<tangerine.length; i++){
            arr[tangerine[i]]++;
        }

        //오름차순 정렬
        Arrays.sort(arr);
        
        //각 인덱스를 더해서 k보다 높거나 같으면 해당 더한 갯수를 return
        int sum = 0;
        int count = 0;
        for(int i=arr.length-1; i>0; i--){
            sum+=arr[i];
            count++;
            if(sum>=k){
                return count;
            }
        }

        return 0;
    }
}
