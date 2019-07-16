package com.design.proxy;

import com.design.proxy.interfaces.IGamePlayer;

/**
 * @author wcg
 * @CreateDate 2019/6/26-17:42
 */
public class GamePlayer implements IGamePlayer {

    private String name = "";

    //�ҵĴ�����˭
    private IGamePlayer proxy = null;

    public GamePlayer(String name) {
        this.name = name;
    }

    //����Ϸ֮ǰ�ĵ�¼,��Ҫ����
    @Override
    public void login(String user, String password) {
        if(this.isProxy()){
            System.out.println("��¼��Ϊ"+user+"���û�" + this.name+"��¼�ɹ���");
        }else{
            System.out.println("��ʹ��ָ���Ĵ������!");
        }
    }

    @Override
    public void killBoss() {
        if(this.isProxy()){
        System.out.println(this.name+" �ڴ��! ");
    }else{
        System.out.println("��ʹ��ָ���Ĵ������!");
    }
}

    @Override
    public void upgrade() {
        if(this.isProxy()){
            System.out.println(this.name+" ������һ��! ");
        }else{
            System.out.println("��ʹ��ָ���Ĵ������!");
        }

    }
    //�ҵ��Լ��Ĵ���
    @Override
    public IGamePlayer getProxy() {
        this.proxy = new GamePlayerProxy(this);
        return this.proxy;
    }

    private Boolean isProxy(){
        if(this.proxy == null){
            return false;
        }else{
            return true;
        }

    }
}
