package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-18:07
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component _component){
        super(_component);
    }

    //定义自己的修饰方法
    private void method2(){
        System.out.println("method2修饰");
    }

    //重写父类得operation方法
    public void operate(){
        this.method2();
        super.operate();
    }
}
