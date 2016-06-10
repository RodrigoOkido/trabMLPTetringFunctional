package tetrisInterfaceFunctional;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import domainClassFunctional.Piece;
import domainClassFunctional.PieceCollection;
import domainClassFunctional.PieceI;
import domainClassFunctional.PieceL;
import domainClassFunctional.PieceLI;
import domainClassFunctional.PieceN;
import domainClassFunctional.PieceNI;
import domainClassFunctional.PieceSquare;
import domainClassFunctional.PieceT;
import domainClassFunctional.Point;


/**
 * Cria o grid do Tetris. Esta classe é responsável por praticamente
 * todos os recursos necessários para rodar o game.
 *
 */
public class Grid extends JPanel{

	
	//Definição de tamanho do grid e pontuação do Game.
	private final static int COLUMNS = 12;
	private final static int ROWS = 23;
	private int score = 0;
	
	//Lista das peças e atributos para controle das peças do game.
	private final static PieceCollection pushPieces = new PieceCollection();
	private final static Piece[] pieceCollection = pushPieces.getList();
	private final Color[] colors = Piece.getPieceColors();
	private static int currentPiece;
	private static int pieceRotation;
	private static Piece actualPiece;
	
	
	//Verificação se o game acabou
	private boolean gameover;
	
	//Formação do Grid.
	private static Color gridMap[][];

	
	/**
	 * Construtor da classe Grid.
	 * 
	 */
	public Grid(){
		gameover = false;
		//inicializa o grid vazio
		gridMap = new Color[COLUMNS][ROWS];
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if (i == 0 || i == COLUMNS-1 || j == ROWS-1) {
					gridMap[i][j] = Color.DARK_GRAY;
				} else {
					gridMap[i][j] = Color.BLACK;
				}
			}
		}
		putNewPieceOnGrid();
	}
	
	
	/**
	 * Escolhe uma peça para inserir no grid. A peça a ser inserida
	 * é feita de forma aleatória.
	 * 
	 */
	public void putNewPieceOnGrid() {
		Random p = new Random();
		int piece = p.nextInt(7);

		currentPiece = piece;
			
		if(pieceCollection[currentPiece] instanceof PieceI){
			actualPiece = new PieceI();
		}
		if(pieceCollection[currentPiece] instanceof PieceL){
			actualPiece = new PieceL();
		}
		if(pieceCollection[currentPiece] instanceof PieceLI){
			actualPiece = new PieceLI();
		}
		if(pieceCollection[currentPiece] instanceof PieceN){
			actualPiece = new PieceN();
		}
		if(pieceCollection[currentPiece] instanceof PieceNI){
			actualPiece = new PieceNI();
		}
		if(pieceCollection[currentPiece] instanceof PieceSquare){
			actualPiece = new PieceSquare();
		}
		if(pieceCollection[currentPiece] instanceof PieceT){
			actualPiece = new PieceT();
		}
		
	}
	
	
	/**
	 * Desenha uma peça dentro do grid.
	 * 
	 * @param g Recebe um parâmetro do tipo Graphics que será utilizado
	 * 		  para desenhar a peça.
	 */
	public void drawPieceOnGrid(Graphics g) {		
		//Colore a respectiva peça encontrada no grid.
		g.setColor(colors[currentPiece]);
		//Pega as coordenadas das peças
		Point[][] getPiece = actualPiece.getPieceCoordinates();
		//Pega a rotação da peça.
		pieceRotation = actualPiece.getRotation();
		
		for (Point p : getPiece[pieceRotation]) {
			g.fillRect((p.getPositionX() + actualPiece.getPieceLocation().getPositionX()) * 26, 
					   (p.getPositionY() + actualPiece.getPieceLocation().getPositionY()) * 26, 
					   25, 25);
			
		}
	}
	
	
	/**
	 * Método que realiza a pintura do grid, exibe o Score do game 
	 * na tela e pinta a peça atual controlada pelo jogador.
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		// Pinta o grid todo. 
		g.fillRect(0, 0, 26*COLUMNS, 26*ROWS);
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				g.setColor(gridMap[i][j]);
				g.fillRect(26*i, 26*j, 25, 25);
			}
		}
		
		// Pinta no grid a peça atualmente ativa pelo jogador.
		drawPieceOnGrid(g);
		
	}
	
	
	/**
	 * Método responsável por fazer a peça parte do grid. Quando uma peça 
	 * atinge o "chão" do grid, ou alguma outra peça, esta peça controlada 
	 * pelo jogador será "grudada" e fará parte do grid com as coordenadas
	 * de onde ela parou.
	 */
	public void makePiecePartOfGrid(){
		Point[][] getPiece = actualPiece.getPieceCoordinates();
		for (Point p : getPiece[pieceRotation]) {
			gridMap[actualPiece.getPieceLocation().getPositionX() + p.getPositionX()][actualPiece.getPieceLocation().getPositionY() + p.getPositionY()] = colors[currentPiece];
		}
		hasFullRow();
		if (gameIsOver() == true){
			stopDropping();
			TetrisApp.getGameStatus().setText("GAME OVER!");
		} else{
			putNewPieceOnGrid();
		}
	}
	

	/**
	 * Verifica se o game acabou.
	 * 
	 * @return Retorna true se as peças atingiram o topo do game, ou false caso contrário.
	 */
	public boolean gameIsOver(){
		for (int i = 1; i < COLUMNS-1; i++) {
			if(gridMap[i][1] != Color.BLACK){				
				gameover = true;
				return gameover;
			}
		}
		return gameover;
	}
	
	
	
	/**
	 * Deleta uma linha dentro do grid indicada por parâmetro.
	 * Caso houver peças acima desta linha, todas as peças localizadas 
	 * acima, descem uma linha para baixo.
	 * 
	 * @param row
	 */
	public void deleteRow(int row){
		for (int j = row-1; j > 0; j--) {
			for (int i = 1; i < 11; i++) {
				gridMap[i][j+1] = gridMap[i][j];
			}
		}	
	}
	
	
	
	/**
	 * Verifica se ha linha(s) completa(s) no grid. Caso possua, 
	 * estas linhas serão deletadas e o jogador pontua de acordo 
	 * com o número de linhas que serão deletadas. Neste caso,
	 * o máximo possível são 4 linhas (Peça com maior tamanho
	 * vertical - Peça I)
	 * 
	 * @return True possui linha cheia. False caso contrário.
	 */
	public boolean hasFullRow(){
		boolean gap;
		int numClears = 0;
		
		for (int j = 21; j > 0; j--) {
			gap = false;
			for (int i = 1; i < 11; i++) {
				if (gridMap[i][j] == Color.BLACK) {
					gap = true;
					break;
				}
			}
			if (!gap) {
				deleteRow(j);
				j += 1;
				numClears += 1;
			}
		}
		
		switch (numClears) {
		case 1:
			setScore(50);
			break;
		case 2:
			setScore(150);
			break;
		case 3:
			setScore(300);
			break;
		case 4:
			setScore(500);
			break;
		}
		
		
		return false;
	}
	
	
	/**
	 * Método para derrubar no grid a peça atual. Ela adquire as 
	 * coordenadas X e Y atuais da peça, e verifica se ela ainda pode
	 * manter caindo verificando se possui colisão. Caso negativo, ela
	 * irá cair uma posição, caso contrário ela será fixada no grid.
	 * 
	 */
	public void dropPieceOnGrid() {
		int coordXPiece = actualPiece.getPieceLocation().getPositionX();
		int coordYPiece = actualPiece.getPieceLocation().getPositionY();
		if (!collidesAtSomething(coordXPiece, coordYPiece + 1, pieceRotation)) {
			actualPiece.dropPiece();
		} else {
			makePiecePartOfGrid();
		}	
		repaint();
	}
	
	
	/**
	 * Para uma peça de continuar caindo dentro do grid.
	 */
	public void stopDropping(){
		actualPiece.stopPiece();
	}
	
	
	/**
	 * Rotaciona uma peça dentro do grid. Recebe a peça atual controlada pelo jogador
	 * e chama o método que executa de fato a rotação da peça. Localizada na classe Piece.
	 * Após realizada a rotação, a peça é repintada no grid.
	 * 
	 * @param r Recebe um valor r que determinará a rotação.
	 */
	public void rotate(int r){
		actualPiece.rotatePiece(r);
		pieceRotation = actualPiece.getRotation();
		repaint();
	}
	
	
	/**
	 * Método responsável por mover uma peça e repintar sua nova posição dentro do grid.
	 * A partir da peça atual controlada pelo jogador, este método será executado quando
	 * o jogador desejar mover a peça para esquerda ou direita. A peça atual chama o método
	 * movePiece(int d) implementada na classe Piece. 
	 * 
	 * @param d Recebe um inteiro que representará o deslocamento que a peça fará dentro do grid.
	 */
	public void moveLeftOrRight(int d){
		actualPiece.movePiece(d);
		repaint();
	}
	
	
	
    /**
     * Método para verificar se uma peça colide com algo. Dentre
     * as opções pode ser os limites do grid, ou outras peças.
     * 
     * @param x Coordenada X atual da peça.
     * @param y Coordenada Y atual da peça.
     * @param rotation Rotação atual da peça.
     * @return Retorna true caso haja colisão possível na proxima posição. 
     * 		   Caso contrário retornará false.
     */
	public static boolean collidesAtSomething(int x, int y, int rotation) {
		Point[][] getPiece = actualPiece.getPieceCoordinates();
		for (Point p : getPiece[rotation]) {
			if (gridMap[p.getPositionX() + x][p.getPositionY() + y] != Color.BLACK) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Retorna o número de linhas do grid.
	 * 
	 * @return Retorna o número de linhas.
	 */
	public int getROWS(){
		return ROWS;
	}
	
	
	
	/**
	 * Retorna o número de colunas do grid.
	 * 
	 * @return Retorna o número de colunas.
	 */
	public int getColumns(){
		return COLUMNS;
	}
	
	
	
	/**
	 * Retorna o score do game atual.
	 * 
	 * @return Retorna o score.
	 */
	public int getScore(){
		return score;
	}
	
	
	public boolean getGameStatus(){
		return gameover;
	}
	
	
	/**
	 * Atualiza o score do game atual.
	 *  
	 * @param value Recebe um valor a ser somado com o Score atual do jogador.
	 */
	public void setScore(int value){
		score = score+value;
	}
    
}

