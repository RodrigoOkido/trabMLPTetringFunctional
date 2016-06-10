package eventsControllerFunctional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import tetrisInterfaceFunctional.Grid;
import tetrisInterfaceFunctional.TetrisApp;

/**
 * Classe Controller. Responsável por toda a resposta de inputs e controle do game.
 * 
 */
public class Controller implements ActionListener, KeyListener {

	//Recebe o grid do game para controlar.
	private Grid tetrisGame;
	
	//Recebe o App
	private TetrisApp game;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param a Recebe um App do game Tetris. (Classe main)
	 * @param b Recebe um Grid (Neste caso o grid do game Tetris)
	 */
	public Controller(TetrisApp a,Grid b){
		tetrisGame = b;
		game = a;
	}
	
	/**
	 * Controla as ações quando o usuário clica algum botão.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		actionButton.actionPerformed(e);      
	}
	
	/**
	 * Responde as ações do usuário durante a execução do Tetris.
	 * Responde a movimentação das peças dentro do grid. 
	 */
	@Override
	public void keyPressed(KeyEvent tecla) {
		getKey.keyPressed(tecla);
		
	}

	//Não foi utilizado.
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//Não foi utilizado.
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	// ***** PARTE FUNCIONAL - CLASSE CONTROLLER *****
	
	ActionListener actionButton = (ActionEvent e) -> {
		   if(e.getActionCommand().equals("Start Game")){
	            try {
	            	if(!tetrisGame.getGameStatus()){
	            		game.setController();
	                	new Thread() {
	            			@Override public void run() {
	            				while (true) {
	            					try {
	            						Thread.sleep(1000);
	            						tetrisGame.dropPieceOnGrid();    					
	            					} catch ( InterruptedException e ) {}
	            				}
	            			}
	            		}.start();
	            		} else{
	            			return;
	            		}       
	            } catch (Exception e1) {
				
				}                  
	        };};
	        
	        
	TakeButtonKeyBoard getKey = (KeyEvent tecla) -> {
		switch (tecla.getKeyCode()) {
		case KeyEvent.VK_UP:
			tetrisGame.rotate(-1);
			break;
		case KeyEvent.VK_DOWN:
			tetrisGame.dropPieceOnGrid();
			if(!tetrisGame.getGameStatus()){
				tetrisGame.setScore(1);
				String sc = Integer.toString(tetrisGame.getScore());
				game.getScorePoints().setText(sc);
			} 
			break;
		case KeyEvent.VK_LEFT:
			tetrisGame.moveLeftOrRight(-1);
			break;
		case KeyEvent.VK_RIGHT:
			tetrisGame.moveLeftOrRight(+1);
			break;
		} 
	};
	 
	
	interface TakeButtonKeyBoard{
		void keyPressed(KeyEvent tecla);
	}
	
	}


