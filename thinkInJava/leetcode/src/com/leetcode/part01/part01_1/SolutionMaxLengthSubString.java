package com.leetcode.part01.part01_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *����һ���ַ����������ҳ����в������ظ��ַ��� ��Ӵ� �ĳ��ȡ�
 *
 * ����һ���ַ����������ҳ����в������ظ��ַ���?��Ӵ�?�ĳ��ȡ�
 *
 * ʾ��?1:
 *
 * ����: "abcabcbb"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "abc"�������䳤��Ϊ 3��
 * ʾ�� 2:
 *
 * ����: "bbbbb"
 * ���: 1
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "b"�������䳤��Ϊ 1��
 * ʾ�� 3:
 *
 * ����: "pwwkew"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ���?"wke"�������䳤��Ϊ 3��
 * ?    ��ע�⣬��Ĵ𰸱����� �Ӵ� �ĳ��ȣ�"pwke"?��һ�������У������Ӵ���
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class SolutionMaxLengthSubString {


    /*����һ��������
    ��Ŀ���º�����ʱ�����ƣ������ TLE��

    ˼·

    ���������е����ַ����������Ƿ񲻺����ظ����ַ���

    �㷨

    ����������һ������ boolean allUnique(String substring) ��������ַ����е��ַ�����Ψһ�ģ�
    ���᷵�� true������᷵�� false�� ���ǿ��Ա��������ַ��� s �����п��ܵ����ַ��������ú��� allUnique��
     �����ʵ֤������ֵΪ true����ô���ǽ���������ظ��ַ��Ӵ�����󳤶ȵĴ𰸡�

    �����������ȱ�ٵĲ��֣�

    Ϊ��ö�ٸ����ַ������������ַ�����������Ҫö�����ǿ�ʼ�ͽ��������������迪ʼ�ͽ����������ֱ�Ϊ ii �� jj��
    ��ô������ 0 \leq i \lt j \leq n0��i<j��n������Ľ������� jj �ǰ������ų��ģ���
    ��ˣ�ʹ�� ii �� 0 �� n - 1n?1 �Լ� jj �� i+1i+1 �� nn ������Ƕ�׵�ѭ�������ǿ���ö�ٳ� s ���������ַ�����

    Ҫ���һ���ַ����Ƿ����ظ��ַ������ǿ���ʹ�ü��ϡ����Ǳ����ַ����е������ַ������������������set �С�
    �ڷ���һ���ַ�֮ǰ�����Ǽ��ü����Ƿ��Ѿ���������������������ǻ᷵�� false��ѭ�����������Ƿ��� true��
    */

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
/*
    ���Ӷȷ���

    ʱ�临�Ӷȣ�O(n^3)O(n
3
        ) ��

    Ҫ��֤������Χ�� [i, j)[i,j) �ڵ��ַ��Ƿ���Ψһ�ģ�������Ҫ���÷�Χ�е������ַ��� ��ˣ��������� O(j - i)O(j?i) ��ʱ�䡣

    ���ڸ����� i���������� j \in [i+1, n]j��[i+1,n] ���ķѵ�ʱ���ܺ�Ϊ��

            \sum_{i+1}^{n}O(j - i)
    i+1
            ��
    n
?
    O(j?i)

    ��ˣ�ִ�����в����ȥ��ʱ���ܺ�Ϊ��

    O\left(\sum_{i = 0}^{n - 1}\left(\sum_{j = i + 1}^{n}(j - i)\right)\right) = O\left(\sum_{i = 0}^{n - 1}\frac{(1 + n - i)(n - i)}{2}\right) = O(n^3)
    O(
            i=0
            ��
            n?1
            ?
            (
            j=i+1
            ��
            n
            ?
            (j?i)))=O(
            i=0
            ��
            n?1
            ?

            2
            (1+n?i)(n?i))=O(n3)

    �ռ临�Ӷȣ�O(min(n, m))O(min(n,m))��������Ҫ O(k)O(k) �Ŀռ���������ַ������Ƿ����ظ��ַ������� kk ��ʾ Set �Ĵ�С���� Set �Ĵ�Сȡ�����ַ��� nn �Ĵ�С�Լ��ַ���/��ĸ mm �Ĵ�С��

   */


   /* ����������������
            �㷨

    �������ǳ��򵥣�����̫���ˡ���ô���Ǹ�����Ż����أ�

    �ڱ������У����ǻᷴ�����һ�����ַ����Ƿ������ظ����ַ���������û�б�Ҫ�ġ���������� ii �� j - 1j?1 ֮������ַ��� s_{ij}s
            ij
?
    �Ѿ������Ϊû���ظ��ַ�������ֻ��Ҫ��� s[j]s[j] ��Ӧ���ַ��Ƿ��Ѿ����������ַ��� s_{ij}s
            ij
?
    �С�

    Ҫ���һ���ַ��Ƿ��Ѿ������ַ����У����ǿ��Լ���������ַ������⽫����һ�����Ӷ�Ϊ O(n^2)O(n
2
        ) ���㷨�������ǿ������ø��á�

    ͨ��ʹ�� HashSet ��Ϊ�������ڣ����ǿ����� O(1)O(1) ��ʱ������ɶ��ַ��Ƿ��ڵ�ǰ�����ַ����еļ�顣

    ��������������/�ַ��������г��õĳ����� ����ͨ����������/�ַ������ɿ�ʼ�ͽ������������һϵ��Ԫ�صļ��ϣ��� [i, j)[i,j)����գ��ҿ����������������ǿ��Խ������߽���ĳһ���򡰻������Ĵ��ڡ����磬���ǽ� [i, j)[i,j) ���һ��� 11 ��Ԫ�أ���������Ϊ [i+1, j+1)[i+1,j+1)����գ��ҿ�����

    �ص����ǵ����⣬����ʹ�� HashSet ���ַ��洢�ڵ�ǰ���� [i, j)[i,j)����� j = ij=i���С� Ȼ���������Ҳ໬������ jj����������� HashSet �У����ǻ�������� jj��ֱ�� s[j] �Ѿ������� HashSet �С���ʱ�������ҵ���û���ظ��ַ�������ַ������������� ii ��ͷ��������Ƕ����е� ii ���������Ϳ��Եõ��𰸡�
*/

     /*
        i=0,j=0 ans =0 set:
        i=0,j=1 ans =1 set:a    ������a
        i=0,j=2 ans =2 set:ab   ������b
        i=0,j=3 ans =3 set:abc  ������c
        i=1,j=3 ans =3 set:bc   ����a
        i=1,j=4 ans =3 set:bca  �Ƴ�a֮�� ������a ans=4-1=3
        i=2,j=4 ans =3 set:ca   ����b �����Ƴ�b
        i=2,j=5 ans =3 set:cab
        i=3,j=5 ans =2 set:ab
        i=3,j=6 ans =3 set:abc






     */

    public static int lengthOfLongestSubstring1(String s) { //abcabcde
        int n = s.length();                                 //01234567
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /*���������Ż��Ļ�������
    �����ķ��������Ҫִ�� 2n �����衣��ʵ�ϣ������Ա���һ���Ż�Ϊ����Ҫ n �����衣���ǿ��Զ����ַ���������ӳ�䣬������ʹ�ü������ж�һ���ַ��Ƿ���ڡ� �������ҵ��ظ����ַ�ʱ�����ǿ������������ô��ڡ�

    Ҳ����˵����� s[j]s[j] �� [i, j)[i,j) ��Χ������ j'j
            ��
    �ظ����ַ������ǲ���Ҫ������ ii �� ���ǿ���ֱ������ [i��j'][i��j
            ��
            ] ��Χ�ڵ�����Ԫ�أ����� ii ��Ϊ j' + 1j
            ��
            +1��*/

/*

 */
/*
        n=9,j=0,i=0,ans=1,map:a->1
        n=9,j=1,i=0,ans=2,map:a->1,b->2,

        n=9,j=2,i=0,ans=3,map:a->1,b->2,c->3
        n=9,j=3,i=1,ans=3,map:a->4,b->2,c->3
        n=9,j=4,i=4,ans=3,map:a->5,b->2,c->3
        n=9,j=5,i=4,ans=3,map:a->5,b->6,c->3
        n=9,j=6,i=4,ans=3,map:a->5,b->6,c->3

 */

    public static int lengthOfLongestSubstring2(String s) { //abcaabcde
        int n = s.length(), ans = 0;                        //012345678
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                    if (map.containsKey(s.charAt(j))) {
                        i = Math.max(map.get(s.charAt(j)), i);
                    }
                ans = Math.max(ans, j - i + 1);
                map.put(s.charAt(j), j + 1);
             }
        return ans;
    }






    public static void main(String[] args) {

//        ����һ��������
        System.out.println(lengthOfLongestSubstring2("abcaabcde"));

    }







}
