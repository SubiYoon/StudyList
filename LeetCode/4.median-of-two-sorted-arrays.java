
// @leet start
import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];

        for (int i = 0; i < arr.length; i++) {
            if (i <= nums1.length - 1) {
                arr[i] = nums1[i];
            } else {
                arr[i] = nums2[i - nums1.length];
            }
        }

        Arrays.sort(arr);

        int checkIndex = 0;
        int result = -1;
        for (int num : arr) {
            if (arr.length == 1) {
                return (double) num;
            }
            checkIndex++;
            if (arr.length != 2 && (checkIndex == 1 || checkIndex == arr.length)) {
                continue;
            } else {
                if (arr.length % 2 == 0) {
                    result += num;
                } else {
                    break;
                }
            }
        }
        return result == -1 ? (double) arr[arr.length / 2] : (double) (result + 1) / (arr.length - 2);
    }
}
// @leet end
