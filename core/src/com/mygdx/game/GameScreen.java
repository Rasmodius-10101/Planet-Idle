package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //variables
    //screens
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
//    private Texture background;
    private Texture[] backgrounds;
    private Texture planet;

    //timing
//    private int backgroundOffset;
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;
    private float rotateAngle;
    private float rotateAngleSpeed =2;



    //world parameters
    private final int WORLD_WIDTH = 72, WORLD_HEIGHT = 128;


    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

//        background = new Texture("core/assets/space.png");
//        backgroundOffset = 0;
        backgrounds= new Texture[4];
        backgrounds[0] = new Texture("core/assets/space.png");
        backgrounds[1] = new Texture("core/assets/space02.png");
        backgrounds[2] = new Texture("core/assets/space02.png");
        backgrounds[3] = new Texture("core/assets/space02.png");

        planet = new Texture("core/assets/earth.png");

        backgroundMaxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;


        batch = new SpriteBatch();
    }


    @Override
    public void render(float deltatime) {
        batch.begin();

        //scrolling background
        renderBackground(deltatime);
        batch.draw(planet,10,60,50,50);

        batch.end();
    }

    private void renderBackground(float deltatime) {
        backgroundOffsets[0] += deltatime * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += deltatime * backgroundMaxScrollingSpeed / 5;
        backgroundOffsets[2] += deltatime * backgroundMaxScrollingSpeed / 3;
        backgroundOffsets[3] += deltatime * backgroundMaxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer++){
            if (backgroundOffsets[layer] > WORLD_HEIGHT){
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer],WORLD_WIDTH,WORLD_HEIGHT);
            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer]+WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
}
