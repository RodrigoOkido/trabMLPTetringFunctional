package tetrisInterfaceFunctional;

import javax.swing.*;

import eventsControllerFunctional.Controller;
import java.awt.Color;
import java.awt.Font;


/**
 * Classe TetrisApp. Classe onde se localiza a main do programa.
 * Inicializa tudo que é necessário para exibir as informações ao 
 * usuário na tela. E inicia o game Tetris.
 *
 */
public class TetrisApp {

	//JFrame onde será colocado todos os dados.
	private static JFrame guiFrame;
	
	//JPanel para exibição da pontuação.
	private static JPanel scorePanel;
	
	//JPanel para a exibição do botão.
	private static JPanel buttonPanel;
	
	//JButton para ser inserido dentro da JPanel buttonPanel.
	private static JButton newGame;
	
	//JPanel do grid completo do game.
	private static Grid gridPanel;
	
	//JLabel para indicar o Score.
	private JLabel gameScoreViewer;
	
	//JLabel para exibir quando o game acabar.
	private static JLabel endGame;
	
	//JTextPane para exibir de fato o score do usuário.
	private JTextPane scorePoints;
	
	//Atributo de Controle do game. 
	private static Controller c;

	/**
	 * Construtor. da classe TetrisApp. É apenas usado para 
	 * permitir o uso de seus métodos. Não inicializa nada 
	 * dentro deste construtor.
	 */
	public TetrisApp(){
	}
	
	/**
	 * Retorna o JButton newGame.
	 * 
	 * @return Retorna o botão.
	 */
	public static JButton getPlayButton(){
		return newGame;
	}
	
	
	/**
	 * Retorna a pontuação do game.
	 * 
	 * @return Retorna a pontuação.
	 */
	public JTextPane getScorePoints(){
		return scorePoints;
	}
	

	/**
	 * Retorna o status do game.
	 * 
	 * @return Retorna o status.
	 */
	public static JLabel getGameStatus(){
		return endGame;
	}
	
	
	/**
	 * Método que inicia o processo do game.
	 */
	public void startGame(){
		guiFrame = new JFrame("TETRIS by Tetring Group");	
		gridPanel = new Grid();
		prepareGUI();
	}
	
	
	/**
	 * Método que prepara toda a janela do game.
	 */
	public void prepareGUI(){	

		int ROWS = gridPanel.getROWS();
		int COLUMNS = gridPanel.getColumns();
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setSize(COLUMNS*26+180, 26*ROWS+25);
		guiFrame.setResizable(false);
		guiFrame.setLocationRelativeTo(null);//abre a janela no meio da tela
			
		gridPanel.setLayout(null);		

		scorePanel = new JPanel();
		scorePanel.setBounds(320, 11, 151, 66);
		gridPanel.add(scorePanel);
		scorePanel.setLayout(null);
		
		gameScoreViewer = new JLabel(">> GAME SCORE <<");
		gameScoreViewer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gameScoreViewer.setBounds(10, 11, 126, 14);
		scorePanel.add(gameScoreViewer);
		
		endGame = new JLabel("");
		endGame.setForeground(Color.RED);
		endGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		endGame.setBounds(320, 311, 121, 37);
		gridPanel.add(endGame);
		
		scorePoints = new JTextPane();
		scorePoints.setBounds(10, 36, 116, 19);
		scorePanel.add(scorePoints);
		scorePoints.setText("0");
		scorePoints.setBackground(UIManager.getColor("menu"));

		buttonPanel = new JPanel();
		buttonPanel.setBounds(320, 99, 151, 105);
		gridPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		newGame = new JButton("Start Game");
		newGame.setBounds(10, 37, 131, 36);
		buttonPanel.add(newGame);
		
		guiFrame.getContentPane().add(gridPanel);

		
		gridPanel.setFocusable(true);
		guiFrame.setVisible(true);
	}
	
	/**
	 * Método que certifica que o controle mantenha o foco no grid.
	 * O programa deve manter o foco nesta JPanel para que haja 
	 * resposta aos comandos do usuário durante o game.
	 */
	public void setController(){
		scorePoints.setFocusable(false);
		gameScoreViewer.setFocusable(false);
		newGame.setFocusable(false);
		scorePanel.setFocusable(false);
		buttonPanel.setFocusable(false);
		gridPanel.setFocusable(true);
		gridPanel.addKeyListener(c);
	}
	
	
	/**
	 * Main. Inicia o jogo.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		TetrisApp gameTetris = new TetrisApp();
		gameTetris.startGame();
		c = new Controller(gameTetris,gridPanel);
		
		newGame.addActionListener(c);
	
	}
}
