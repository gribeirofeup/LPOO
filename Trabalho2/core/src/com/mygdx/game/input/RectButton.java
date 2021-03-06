package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO: Auto-generated Javadoc
/**
 * Representa um botao a ser usado numa interface grafica.
 */
public class RectButton {

    /** The y2. */
    int x1,y1,x2,y2;
    
    /** Textura do botao quando nao está a ser carregado. */
    Texture buttonidle;
    
    /** Textura do botao quando está a ser carregado. */
    Texture buttonpressed;
    
    /** Som feito ao ser largado. */
    Sound click;

    /**
     * Instantiates a new rect button.
     *
     * @param x1 the x1
     * @param y1 the y1
     * @param x2 the x2
     * @param y2 the y2
     * @param a the a
     * @param sound the sound
     */
    public RectButton(int x1,int y1,int x2,int y2, String a,boolean sound){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if(sound == true)
            click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
        else click = null;

        if(a != "null"){
            buttonidle = new Texture(a);
            buttonpressed = new Texture(a);
        }else{
            buttonidle = null;
            buttonpressed = null;
        }

    }
    
    /**
     * Instantiates a new rect button.
     *
     * @param x1 the x1
     * @param y1 the y1
     * @param x2 the x2
     * @param y2 the y2
     * @param a the a
     * @param b the b
     * @param sound the sound
     */
    public RectButton(int x1,int y1,int x2,int y2, String a, String b,boolean sound){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if(sound == true)
            click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
        else click = null;
        buttonidle = new Texture(a);
        buttonpressed = new Texture(b);

    }

    /**
     * Retorna true se o botao estiver a ser tocado, false se nao.
     *
     * @return true, if is pressed
     */
    public boolean isPressed(){
        for(int i = 0;i < 10;i++){
            if(!Gdx.input.isTouched(i))
                continue;
            float x = Gdx.input.getX(i);
            float y = Gdx.input.getY(i);
            if(x > x1 &&
                    x < x2 &&
                    y > y1 &&
                    y < y2){
                if(click != null){
                    click.stop();
                    click.play();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Render ao botao.
     *
     * @param s the s
     */
    public void render(SpriteBatch s){
        if(buttonidle == null)
            return;
        if(this.isPressed()){
            s.draw(buttonpressed,x1,-(-Gdx.graphics.getHeight()/2+y1)+Gdx.graphics.getHeight()/2-(y2-y1),x2-x1,y2-y1);
        }else{
            s.draw(buttonidle,x1,-(-Gdx.graphics.getHeight()/2+y1)+Gdx.graphics.getHeight()/2-(y2-y1),x2-x1,y2-y1);
        }
    }

    /**
     * Gets the buttonidle.
     *
     * @return the buttonidle
     */
    public Texture getButtonidle() {
        return buttonidle;
    }


    /**
     * Gets the buttonpressed.
     *
     * @return the buttonpressed
     */
    public Texture getButtonpressed() {
        return buttonpressed;
    }

    /**
     * Dispose.
     */
    public void dispose(){
        if(buttonidle != null){
            buttonidle.dispose();
        }
        if(buttonpressed != null){
            buttonpressed.dispose();
        }
        if(click != null)
            click.dispose();
    }

}
