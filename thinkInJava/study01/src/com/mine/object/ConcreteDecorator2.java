package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-18:07
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component _component){
        super(_component);
    }

    //�����Լ������η���
    private void method2(){
        System.out.println("method2����");
    }

    //��д�����operation����
    public void operate(){
        this.method2();
        super.operate();
    }
}
