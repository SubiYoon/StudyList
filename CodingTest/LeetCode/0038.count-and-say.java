/*
                                  https://leetcode.com/problems/count-and-say/
                                                        
                                               38. Count and Say
                                Medium │ 4188  8500  │ 57.2% of 1.8M │ 󰛨 Hints



The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

	* countAndSay(1) = "1"
	
	* countAndSay(n) is the run-length encoding of countAndSay(n - 1).

[Run-length encoding](http://en.wikipedia.org/wiki/Run-length_encoding) (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".

Given a positive integer n, return the n^th element of the count-and-say sequence.



󰛨 Example 1:

Input: n = 4

Output: "1211"

Explanation:

	│ countAndSay(1) = "1"
	│ countAndSay(2) = RLE of "1" = "11"
	│ countAndSay(3) = RLE of "11" = "21"
	│ countAndSay(4) = RLE of "21" = "1211"

󰛨 Example 2:

Input: n = 1

Output: "1"

Explanation:

This is the base case.



 Constraints:

	* 1 <= n <= 30



Follow up: Could you solve it iteratively?

*/

// @leet start
class Solution {
    int count = 1;

    public String countAndSay(int n) {
        String result = "1";
        StringBuilder sb = new StringBuilder();

        char currentNum = '1';
        byte currentNumCount = 0;

        while (count < n) {
            if (n == 1) {
                return result;
            }

            for (int i = 0; i < result.length(); i++) {
                if (currentNum == result.charAt(i)) {
                    currentNumCount++;
                } else {
                    sb.append(currentNumCount);
                    sb.append(currentNum);

                    currentNum = result.charAt(i);
                    currentNumCount = 1;
                }
            }

            sb.append(currentNumCount);
            sb.append(currentNum);

            count++;

            result = sb.toString();

            sb = new StringBuilder();
            currentNum = result.charAt(0);
            currentNumCount = 0;
        }

        return result;
    }
}
// @leet end
