package com.tdop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.ScreenUtils;

/*
* Atualmente nesse codigo, está apenas umm imagem indicando que não há nada,
* uma versão jogável ainda está em desenvolvimento.
*/

public class MainApp extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture fundo;
	private Rectangle fundo_ret;
	//private Stage stage;
	private TextField texto;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		//stage = new Stage();
		//Gdx.input.setInputProcessor(stage);

		batch =  new SpriteBatch();
		fundo = new Texture("tela_de_fundo.png");
		fundo_ret = new Rectangle();
		fundo_ret.height = 720;
		fundo_ret.width = 1080;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, 720);

		/*
		Skin skin = new Skin();
		TextFieldStyle style = new TextFieldStyle();
		style.fontColor = Color.WHITE; //Define a cor do texto.
		skin.add("default", style);
		texto = new TextField("Não há nada aqui ainda...", skin);
		texto.setSize(300, 80);
		*/
		//stage.addActor(texto);


	}

	@Override
	public void render () {
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(fundo, 0, 0);
		batch.end();
		
		/* 
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fundo.dispose();
		//stage.dispose();
	}
}
