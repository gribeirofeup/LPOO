package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.auxclass.Inputs;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.network.GameClient;


/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private final static float RATE = 50;
    private GameClient client;
    private GUIGame gamerenderer;
    private World world;
    private double ratecounter;
    public StateGameMultiplayer(StateManager s) {
        super(s);

        ratecounter = 0;
        gamerenderer = new GUIGame();
        try {
            client = new GameClient();
        }
        catch(Exception e){

        }
    }


    @Override
    public void handleInput(){
        Inputs i = new Inputs(Functions.leftButtonPressed(),Functions.rightButtonPressed(),Functions.jumpButtonPressed(),Functions.powerButtonPressed());
        try{
            client.proxy.sendInput(client,i);
        }catch(Exception e){

        }

    }


    @Override
    public void update(double dt) {
        ratecounter += dt/1000;
        if(ratecounter >= RATE){
            handleInput();
            client.proxy.transferWorld(client);
        }

    }

    @Override
    public void render() {
        gamerenderer.render(client.getWorld());
    }

    @Override
    public void dispose(){
    }
}
