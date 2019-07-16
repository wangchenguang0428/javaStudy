package com.leetcode.part01.part01_1;

/**
 * ��ȡ����֮��
 * @author wcg
 * @CreateDate 2019/7/2-10:18
 */
public class SolutionTwoSum {


    /*
        ����һ���������� nums?��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ����?����?���������������ǵ������±ꡣ

    ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�

    ʾ��:

    ���� nums = [2, 7, 11, 15], target = 9

    ��Ϊ nums[0] + nums[1] = 2 + 7 = 9
    ���Է��� [0, 1]

    ��Դ�����ۣ�LeetCode��
    ���ӣ�https://leetcode-cn.com/problems/two-sum
    ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     */


    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((target - nums[i]) == nums[j]) {
                    return new int[]{i, j};
                }

            }
        }

        throw new IllegalArgumentException("No two sum solution");

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        int[] answer = SolutionTwoSum.twoSum(nums, target);
        for (int i : answer) {
            System.out.println(i);

        }

    }


}
