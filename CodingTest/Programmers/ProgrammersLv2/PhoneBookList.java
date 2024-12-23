package Algorithm.ProgrammersLv2;

import java.util.*;

class PhoneBookList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Set<String> set = new HashSet<>();

        // 길이순서대로 정렬
        Arrays.sort(phone_book, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        int minLength = phone_book[0].length();

        for(String num : phone_book) {
            for(int i = minLength; i < num.length(); i++) {
                String prefix = num.substring(0, i);

                if(set.contains(prefix)){
                    return false;
                }
            }

            set.add(num);
        }

        return answer;

    }
}