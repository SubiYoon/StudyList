package Algorithm.ProgrammersLv2;

import java.util.LinkedList;
import java.util.List;

/*
뉴스 클러스터링
자카드유사도
1. 두 집합 A,B 사이의 두집합의 교집합의 크기를 두집합의 합집합의 크기로 나눈 값
2. 두 집합이 공집합일 경우 1로 정의
3. 중복 원소를 허용한다.
이를 이용하여 문자열 사이의 유사도를 계산하는데 이용가능
두글자씩 끊어서 다중 집합을 만든다.
ex) FRANCE, FRENCH 일경우 {FR, RA, AN, NC, CE}, {FR, RE, EN, NC, CH}
유사도에 65536을 곱하가고 소수점 아래는 버리고 return
조건
1. 영문으로된 글자쌍만 유효 -> 공백, 숫자, 특수문자가 들어있는 경우 그 쌍을 버림
2. 대소문자 구별 안함
 */
public class NewsClustering {
  public int solution(String str1, String str2) {
    //대소문자 구분을 없애기 위해 toUpperCase()사용
    str1 = str1.toUpperCase();
    str2 = str2.toUpperCase();

    //str1을 2개씩 쌍으로 담을 list1
    List<String> list1 = new LinkedList<>();
    for (int i = 0; i < str1.length() - 1; i++) {
      if (str1.substring(i, i + 1).matches("[A-Z]") && str1.substring(i + 1, i + 2).matches("[A-Z]")) {
        list1.add(str1.substring(i, i + 2));
      }
    }
    //교집합을 담을 listN
    List<String> listN = new LinkedList<>();

    //str2을 2개씩 쌍으로 담을 list2
    List<String> list2 = new LinkedList<>();
    for (int i = 0; i < str2.length() - 1; i++) {
      if (str2.substring(i, i + 1).matches("[A-Z]") && str2.substring(i + 1, i + 2).matches("[A-Z]")) {
        list2.add(str2.substring(i, i + 2));
      }
    }
    //합집합을 담을 listU
    List<String> listU = new LinkedList<>();
    for(int i=0; i<list2.size(); i++){
      listU.add(list2.get(i));
    }

    //교집합
    for(int i=0; i<list1.size(); i++){
      for(int j=0; j<list2.size(); j++){
        if(list1.get(i).equals(list2.get(j))){
          listN.add(list1.get(i));
          list2.remove(j);
          break;
        }
      }
    }
    //합집합
    listU.addAll(list1);

    for(int i=0; i<listN.size(); i++){
      for(int j=0; j<listU.size(); j++){
        if(listN.get(i).equals(listU.get(j))){
          listU.remove(j);
          break;
        }
      }
    }

    if(listN.isEmpty() && listU.isEmpty()){
      return 65536;
    }
    
    double num = (double)listN.size()/listU.size()*65536;

    return (int)num;
  }
}
