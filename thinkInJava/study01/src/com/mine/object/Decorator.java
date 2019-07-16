package com.mine.object;

/**
 * @author wcg
 * @CreateDate 2019/5/21-17:59
 */
public abstract class Decorator extends Component {
    private Component component = null;
    //ͨ�����캯�����ݱ�������
    public Decorator(Component _component){
        this.component = _component;
    }

    //ί�и���������ִ��
    @Override
    public void operate(){
        this.component.operate();
    }
}
