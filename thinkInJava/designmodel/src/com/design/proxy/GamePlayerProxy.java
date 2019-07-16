package com.design.proxy;

import com.design.proxy.interfaces.IGamePlayer;

/**
 * @author wcg
 * @CreateDate 2019/6/26-17:48
 */
public class GamePlayerProxy implements IGamePlayer {

    private IGamePlayer gamePlayer = null;




    public GamePlayerProxy(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();

    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
    }

    @Override
    public IGamePlayer getProxy() {
        return this;
    }
}
