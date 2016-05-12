package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;


//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Game game;
    private GUIGame gameGUI;

    public StateGame(StateManager s) {
        super(s);

        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,100));
        game.getPlayer1().setPower(2);
        gameGUI = new GUIGame();
    }


    @Override
    public void handleInput(){
        if(gameGUI.leftButton.isPressed()){
            game.getPlayer1().inputs.movingLeft = true;
        }else game.getPlayer1().inputs.movingLeft = false;
        if(gameGUI.rightButton.isPressed()){
            game.getPlayer1().inputs.movingRight = true;
        }else game.getPlayer1().inputs.movingRight = false;
        if(gameGUI.jumpButton.isPressed()){
            game.getPlayer1().inputs.jump = true;
        }else game.getPlayer1().inputs.jump = false;
        if(gameGUI.powerButton.isPressed()){
            game.getPlayer1().inputs.power = true;
        }else game.getPlayer1().inputs.power = false;

    }


    @Override
    public void update(double dt) {

        if(!game.gameEnd){
            game.update(dt);
        }else{
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }

    }

    @Override
    public void render() {
        gameGUI.render(game);
    }

    @Override
    public void dispose(){
    }
}
