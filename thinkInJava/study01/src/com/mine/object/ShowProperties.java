package com.mine.object;

/**
 * @author wcg
        * @CreateDate 2018/11/22-22:46
        */
public class ShowProperties {

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(
                System.getProperty("java.library.path"));
    }
}
