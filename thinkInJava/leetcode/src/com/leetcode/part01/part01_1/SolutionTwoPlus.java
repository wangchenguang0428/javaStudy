package com.leetcode.part01.part01_1;

import org.junit.Test;

/**
 * ��������?�ǿ� ������������ʾ�����Ǹ������������У����Ǹ��Ե�λ���ǰ���?����?�ķ�ʽ�洢�ģ��������ǵ�ÿ���ڵ�ֻ�ܴ洢?һλ?���֡�
 * <p>
 * ��������ǽ��������������������᷵��һ���µ���������ʾ���ǵĺ͡�
 * <p>
 * �����Լ���������� 0 ֮�⣬���������������� 0?��ͷ��
 * ʾ����
 * <p>
 * ���룺(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * �����7 -> 0 -> 8
 * ԭ��342 + 465 = 807
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class SolutionTwoPlus {
    /*��������ֽ�ϼ����������ֵĺ��������������ȴ������ЧλҲ�����б� l1l1 �� l2l2 �ı�ͷ��ʼ��ӡ�����ÿλ���ֶ�Ӧ������ 0 \ldots 90��9 �ķ�Χ�ڣ����Ǽ����������ֵĺ�ʱ���ܻ���� ������������磬5 + 7 = 125+7=12������������£����ǻὫ��ǰλ����ֵ����Ϊ 22��������λ carry = 1carry=1 ������һ�ε�������λ carrycarry �ض��� 00 �� 11��������Ϊ����������ӣ����ǵ���λ�����ܳ��ֵ�����Ϊ 9 + 9 + 1 = 199+9+1=19��

    α�������£�

    ����ǰ����ʼ��Ϊ�����б���ƽ�㡣
    ����λ carrycarry ��ʼ��Ϊ 00��
    �� pp �� qq �ֱ��ʼ��Ϊ�б� l1l1 �� l2l2 ��ͷ����
    �����б� l1l1 �� l2l2 ֱ���������ǵ�β�ˡ�
    �� xx ��Ϊ��� pp ��ֵ����� pp �Ѿ����� l1l1 ��ĩβ������ֵ����Ϊ 00��
    �� yy ��Ϊ��� qq ��ֵ����� qq �Ѿ����� l2l2 ��ĩβ������ֵ����Ϊ 00��
    �趨 sum = x + y + carrysum=x+y+carry��
    ���½�λ��ֵ��carry = sum / 10carry=sum/10��
    ����һ����ֵΪ (sum \bmod 10)(summod10) ���½�㣬����������Ϊ��ǰ������һ����㣬Ȼ�󽫵�ǰ���ǰ������һ����㡣
    ͬʱ���� pp �� qq ǰ������һ����㡣
    ��� carry = 1carry=1 �Ƿ������������������򷵻��б�׷��һ���������� 11 ���½�㡣
    �����ƽ�����һ����㡣
    ��ע�⣬����ʹ���ƽ�����򻯴��롣���û���ƽ�㣬������д����������������ʼ����ͷ��ֵ��

    ���ر�ע�����������

    ��������	˵��
    l1=[0,1]l1=[0,1]��l2=[0,1,2]l2=[0,1,2]	��һ���б����һ���б�ʱ
    l1=[]l1=[]��l2=[0,1]l2=[0,1]	��һ���б�Ϊ��ʱ�������ֿ��б�
    l1=[9,9]l1=[9,9]��l2=[1]l2=[1]	������������ܳ��ֶ���Ľ�λ����һ������ױ�����

    ���ߣ�LeetCode
    ���ӣ�https://leetcode-cn.com/problems/two-sum/solution/liang-shu-xiang-jia-by-leetcode/
    ��Դ�����ۣ�LeetCode��
    ����Ȩ���������С���ҵת������ϵ���߻����Ȩ������ҵת����ע��������
    */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;//curr����ǰ���ֵ
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode plusListNode = SolutionTwoPlus.addTwoNumbers(l1,l2);

        System.out.print(plusListNode.val+"->");
        System.out.print(plusListNode.next.val+"->");
        System.out.println(plusListNode.next.next.val);



    }


    @Test
    public void test(){
        System.out.println(15/10);

    }



}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

}
