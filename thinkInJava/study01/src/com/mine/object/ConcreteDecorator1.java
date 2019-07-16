package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-18:04
 */
public class ConcreteDecorator1 extends Decorator{
    public ConcreteDecorator1(Component _component){
        super(_component);
    }

    //�����Լ������η���
    private void method1(){
        System.out.println("method1����");
    }

    //��д�����operation����
    public void operate(){
        this.method1();
        super.operate();
    }


}
