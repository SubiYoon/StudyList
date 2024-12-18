/*

                                https://leetcode.com/problems/add-two-numbers/
                                                       
                                              2. Add Two Numbers
                                   Medium │ 32011  6431  │ 44.5% of 11.5M



You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



󰛨 Example 1:

[img](https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg)

	│ Input: l1 = [2,4,3], l2 = [5,6,4]
	│ Output: [7,0,8]
	│ Explanation: 342 + 465 = 807.

󰛨 Example 2:

	│ Input: l1 = [0], l2 = [0]
	│ Output: [0]

󰛨 Example 3:

	│ Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
	│ Output: [8,9,9,9,0,0,0,1]



 Constraints:

	* The number of nodes in each linked list is in the range [1, 100].
	
	* 0 <= Node.val <= 9
	
	* It is guaranteed that the list represents a number that does not have leading zeros.

 */

// @leet start

import java.math.BigDecimal;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal l1num = new BigDecimal(makeNumber(l1));
        BigDecimal l2num = new BigDecimal(makeNumber(l2));

        String sum = l1num.add(l2num).toString();

        return makeNode(sum, new ListNode());
    }

    String makeNumber(ListNode node) {
        String result = "";

        if (node.next != null) {
            result += makeNumber(node.next);
            result += node.val;
        } else {
            result += node.val;
        }

        return result;
    }

    ListNode makeNode(String sumNumberStr, ListNode node) {
        ListNode result = new ListNode();

        if (!"".equals(sumNumberStr)) {
            result.next = makeNode(sumNumberStr.substring(0, sumNumberStr.length() - 1), new ListNode());
            result.val = Integer.parseInt(sumNumberStr.substring(sumNumberStr.length() - 1, sumNumberStr.length()));
        } else {
            result = null;
        }

        return result;
    }
}
// @leet end
