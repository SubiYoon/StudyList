// @leet start
class Solution {
    public int firstMissingPositive(int[] nums) {
        int smallestNum = 1;
        int biggestNum = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) {
                continue;
            }

            smallestNum = Math.min(smallestNum, nums[i]);
            biggestNum = Math.max(biggestNum, nums[i]);
        }

        return 0;
    }
}
// @leet end
