package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-18:04
 */
public class ConcreteDecorator1 extends Decorator{
    public ConcreteDecorator1(Component _component){
        super(_component);
    }

    //定义自己的修饰方法
    private void method1(){
        System.out.println("method1修饰");
    }

    //重写父类得operation方法
    public void operate(){
        this.method1();
        super.operate();
    }


}
