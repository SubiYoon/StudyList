/*
                 https://leetcode.com/problems/longest-substring-without-repeating-characters/
                                                       
                               3. Longest Substring Without Repeating Characters
                              Medium │ 40522  1952  │ 35.7% of 18.3M │ 󰛨 Hints



Given a string s, find the length of the longest substring without repeating characters.



󰛨 Example 1:

	│ Input: s = "abcabcbb"
	│ Output: 3
	│ Explanation: The answer is "abc", with the length of 3.

󰛨 Example 2:

	│ Input: s = "bbbbb"
	│ Output: 1
	│ Explanation: The answer is "b", with the length of 1.

󰛨 Example 3:

	│ Input: s = "pwwkew"
	│ Output: 3
	│ Explanation: The answer is "wke", with the length of 3.
	│ Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.



 Constraints:

	* 0 <= s.length <= 5 * 10^4
	
	* s consists of English letters, digits, symbols and spaces.

 */
// @leet start

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        } else if (s.length() == 0) {
            return 0;
        }

        int result = 0;
        int length = 0;

        for (int i = 0; i < s.length(); i++) {
            String charAtStr = String.valueOf(s.charAt(i));
            int end = s.lastIndexOf(charAtStr);
            if (i != end) {
                length = getSubStringLength(s.substring(i, end));
            } else {
                length = getSubStringLength(s.substring(i, s.length()));
            }

            result = result < length ? length : result;
        }

        return result;
    }

    int getSubStringLength(String s) {

        Set<Character> charSet = new HashSet<>();

        // 자른 문자열 내부에도 중복문자가 있는지 체크
        for (int i = 0; i < s.length(); i++) {
            // 존재하면 존재하는데 까지만 length를 return
            if (!charSet.add(s.charAt(i))) {
                return i;
            }
        }

        return s.length();
    }
}
// @leet end
