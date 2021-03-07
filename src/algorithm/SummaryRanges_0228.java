package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 228. 汇总区间
 */
public class SummaryRanges_0228 {

    public static List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        if (nums.length == 1) return Collections.singletonList(String.valueOf(nums[0]));
        List<String> list = new ArrayList<>();
        int start = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                if (start == nums[i-1]) {
                    list.add(String.valueOf(start));
                } else {
                    list.add(start + "->" + nums[i-1]);
                }
                start = nums[i];
            }
            if (i == nums.length - 1) {
                if (nums[i] == nums[i - 1] + 1) {
                    list.add(start + "->" + nums[i]);
                } else if (nums[i] > nums[i - 1] + 1) {
                    list.add(String.valueOf(nums[i]));
                }
            }
        }
        return list;
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; ++i){
            if(!(i + 1 < nums.length && nums[i] == nums[i + 1] - 1)){
                if(sb.length() > 0) sb.append("->");
                sb.append(nums[i]);
                ans.add(sb.toString());
                sb = new StringBuilder();
            } else{
                if(sb.length() == 0) sb.append(nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(summaryRanges(new int[]{-1}));
    }
}
