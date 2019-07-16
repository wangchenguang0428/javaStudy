package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-18:08
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        //��һ������
        component = new ConcreteDecorator1(component);
        //�ڶ�������
        component = new ConcreteDecorator2(component);
        //���κ�����
        component.operate();

    }
}
