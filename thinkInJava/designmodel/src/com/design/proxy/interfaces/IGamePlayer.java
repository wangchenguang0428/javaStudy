package com.design.proxy.interfaces;

/**
 * @author wcg
 * @CreateDate 2019/6/26-17:37
 */
public interface IGamePlayer {

    //��¼��Ϸ
    public void login(String user,String password);
    //ɱ��,����������Ϸ����Ҫ��ɫ
    public void killBoss();
    //����
    public void  upgrade();
    //ÿ���˶�������һ���Լ��Ĵ���
    public IGamePlayer getProxy();

}
