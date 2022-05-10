package kw.mulitplay.game.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import kw.mulitplay.game.UI.SnakePanel;
import kw.mulitplay.game.screen.base.BaseScreen;

/**
 * all screen extends BaseScreen
 */
public class LoadingScreen extends BaseScreen {

    @Override
    protected void initView() {
        SnakePanel panel = new SnakePanel();
        stage.addActor(panel);
//        Image image = new Image(new Texture("1.png"));
//        stage.addActor(image);
//        image.addAction(Actions.moveTo(100,100,10));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        enterNextScreen();
    }

    public void enterNextScreen(){

    }
}
