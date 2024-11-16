// @leet start
class Solution {
    public int maxArea(int[] height) {
        int result = 0;
        int width = 0;
        int temp = 0;

        for (int i = 0; i < height.length; i++) {
            width = height.length - 1 - i;

            for (int j = 0; j + width < height.length; j++) {
                temp = width * Math.min(height[j], height[j + width]);

                if (result < temp) {
                    result = temp;
                }
            }
        }

        return result;
    }
}
// @leet end
