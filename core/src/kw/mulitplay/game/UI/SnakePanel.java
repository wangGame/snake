package kw.mulitplay.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;


import kw.mulitplay.game.AI.SnakeAi;
import kw.mulitplay.game.Mode.Node;
import kw.mulitplay.game.Mode.Snake;


public class SnakePanel extends Group{
	Snake snake;
	SnakeAi ai;

	/**
	 * Create the panel.
	 */
	public SnakePanel() {
		Pixmap pixmap = getPixmap(620, 30);
		Image image = new Image(new Texture(pixmap));
		addActor(image);
		snake=new Snake();
		Node n=new Node(10,10);//蛇的初始位置
		snake.getS().add(n);
		snake.setFirst(n);
		snake.setLast(n);
		snake.setTail(new Node(0,10));//last的后一个节点
		snake.setFood(new Node(80,80));//食物初始位置
		ai=new SnakeAi();

		addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				touch = true;
			}
		});
	}

	private Pixmap getPixmap(int i2, int i3) {
		Pixmap pixmap = new Pixmap(i2, i2, Pixmap.Format.RGBA8888);
		for (int i = 0; i < i2; i++) {
			for (int i1 = 0; i1 < i2; i1++) {
				pixmap.drawPixel(i, i1, i3);
			}
		}
		return pixmap;
	}

	boolean touch = false;

	Array<Image> array = new Array<>();

	private float time = 0;

	@Override
	public void act(float delta) {
		super.act(delta);
		time += delta;
		if (time > 0.3F){
			time = 0;

			index=0;
			paintSnake(snake);
			paintFood(snake.getFood());
			int dir=ai.play2(snake,snake.getFood());//选择策略：play ,play1,play2
			snake.move(dir);

		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (!touch)return;
		super.draw(batch,1);
	}
	private int index = 0;
	/**
	 * @param snake
	 */
	public void paintSnake(Snake snake){
		for(Node n:snake.getS()){
			index ++;
			if (array.size>index){
				Image image = array.get(index - 1);
				image.setPosition(n.getX()*3,n.getY()*3);
			}else {
				Pixmap pixmap = getPixmap(20, 100);
				Image image = new Image(new Texture(pixmap));
				array.add(image);
				image.setPosition(n.getX(),n.getY());
				addActor(image);
			}
		}
	}
	/**
	 * 画食物
	 * @param food
	 */
	public void paintFood(Node food){
		index++;
		if (array.size>index){
			Image image = array.get(index - 1);
			image.setPosition(food.getX()*3,food.getY()*3);
		}else {
			Pixmap pixmap = getPixmap(20, 100);
			Image image = new Image(new Texture(pixmap));
			array.add(image);
			addActor(image);
			image.setPosition(food.getX()*3,food.getY()*3);
		}
	}
}
