class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // last real element in nums1
        int j = n - 1;      // last element in nums2
        int k = m + n - 1;  // last slot in nums1

        // Fill nums1 from the back, always placing the larger of the two candidates.
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
