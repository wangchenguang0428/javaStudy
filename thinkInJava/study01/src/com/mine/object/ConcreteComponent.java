package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-17:56
 */
public class ConcreteComponent extends Component{
    @Override
    public void operate() {
        System.out.println("do something!");
    }
}
