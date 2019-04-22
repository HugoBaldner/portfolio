class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int c1 = 0;
        int c2 = 0;
        int[] combine;
        if(nums1.length == 0){
            combine = nums2;
            c2 = nums2.length;
        } else if(nums2.length == 0){
            combine = nums1;
            c1 = nums1.length;
        }else{
            combine = new int[nums1.length + nums2.length];
        }
        
        while(c1 < nums1.length && c2 < nums2.length){
            if(nums1[c1] > nums2[c2]){
                combine[c2+c1] = nums2[c2];
                c2++;
            } else if(nums1[c1] < nums2[c2]){
                combine[c2+c1] = nums1[c1];
                c1++;
            } else{
                combine[c2+c1] = nums1[c1];
                c1++; 
                combine[c2+c1] = nums2[c2];
                c2++;
            }
        }
        while(c1 < nums1.length){
            combine[c2+c1] = nums1[c1];
            c1++;
        }
        while(c2 < nums2.length){
            combine[c2+c1] = nums2[c2];
            c2++;
        }
        if(combine.length % 2 == 1){
            return (double) combine[combine.length / 2]; 
        }
      
        return ((double)combine[-1+combine.length / 2] + (double)combine[combine.length / 2])/2.0;
        
        
    }
}