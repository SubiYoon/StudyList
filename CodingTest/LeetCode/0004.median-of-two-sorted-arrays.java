
/**

                  https://leetcode.com/problems/median-of-two-sorted-arrays/
                                               
                                4. Median of Two Sorted Arrays
                           Hard 󱎖 │ 28959  3253  │ 41.9% of 7.1M



Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



󰛨 Example 1:

	│ Input: nums1 = [1,3], nums2 = [2]
	│ Output: 2.00000
	│ Explanation: merged array = [1,2,3] and median is 2.

󰛨 Example 2:

	│ Input: nums1 = [1,2], nums2 = [3,4]
	│ Output: 2.50000
	│ Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.



 Constraints:

	* nums1.length == m
	
	* nums2.length == n
	
	* 0 <= m <= 1000
	
	* 0 <= n <= 1000
	
	* 1 <= m + n <= 2000
	
	* -10^6 <= nums1[i], nums2[i] <= 10^6

 */
// @leet start
import java.math.BigDecimal;
import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        BigDecimal[] arr = new BigDecimal[nums1.length + nums2.length];

        for (int i = 0; i < arr.length; i++) {
            if (i <= nums1.length - 1 && nums1.length != 0) {
                arr[i] = new BigDecimal(nums1[i]);
            } else if (nums2.length != 0) {
                arr[i] = new BigDecimal(nums2[i - nums1.length]);
            }
        }
        Arrays.sort(arr);

        int checkIndex = 0;
        BigDecimal result = new BigDecimal(-1);
        for (BigDecimal num : arr) {
            if (arr.length == 1) {
                return num.doubleValue();
            } else if (arr.length == 2) {
                return ((arr[0].doubleValue() + arr[1].doubleValue()) / 2);
            }

            checkIndex++;
            if (arr.length != 2 && (checkIndex == 1 || checkIndex == arr.length)) {
                continue;
            } else {
                if (arr.length % 2 == 0) {
                    result = result.add(arr[arr.length / 2]);
                    result = result.add(arr[arr.length / 2 - 1]);
                    break;
                } else {
                    break;
                }
            }
        }
        result = result.add(new BigDecimal(1));

        double dResult = result.doubleValue();

        return dResult == 0.0 ? (arr[arr.length / 2]).doubleValue()
                : dResult / 2;
    }
}
// @leet end
