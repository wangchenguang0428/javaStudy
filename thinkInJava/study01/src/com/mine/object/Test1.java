package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/28-10:34
 */
public class Test1 {


    public static void main(String[] args) {
        String str1 = "��г";
        String str2 = "���";
        String str3 = "��г���";
        String str4;
        str4 = str1 + str2;
        System.out.println(str3==str4);
        str4 = (str1+str2).intern();
        System.out.println(str3==str4);
    }


}