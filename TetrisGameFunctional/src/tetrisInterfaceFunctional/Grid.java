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
 * Cria o grid do Tetris. Esta classe � respons�vel por praticamente
 * todos os recursos necess�rios para rodar o game.
 *
 */
public class Grid extends JPanel{

	
	//Defini��o de tamanho do grid e pontua��o do Game.
	private final static int COLUMNS = 12;
	private final static int ROWS = 23;
	private int score = 0;
	
	//Lista das pe�as e atributos para controle das pe�as do game.
	private final static PieceCollection pushPieces = new PieceCollection();
	private final static Piece[] pieceCollection = pushPieces.getList();
	private final Color[] colors = Piece.getPieceColors();
	private static int currentPiece;
	private static int pieceRotation;
	private static Piece actualPiece;
	
	
	//Verifica��o se o game acabou
	private boolean gameover;
	
	//Forma��o do Grid.
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
	 * Escolhe uma pe�a para inserir no grid. A pe�a a ser inserida
	 * � feita de forma aleat�ria.
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
	 * Desenha uma pe�a dentro do grid.
	 * 
	 * @param g Recebe um par�metro do tipo Graphics que ser� utilizado
	 * 		  para desenhar a pe�a.
	 */
	public void drawPieceOnGrid(Graphics g) {		
		//Colore a respectiva pe�a encontrada no grid.
		g.setColor(colors[currentPiece]);
		//Pega as coordenadas das pe�as
		Point[][] getPiece = actualPiece.getPieceCoordinates();
		//Pega a rota��o da pe�a.
		pieceRotation = actualPiece.getRotation();
		
		for (Point p : getPiece[pieceRotation]) {
			g.fillRect((p.getPositionX() + actualPiece.getPieceLocation().getPositionX()) * 26, 
					   (p.getPositionY() + actualPiece.getPieceLocation().getPositionY()) * 26, 
					   25, 25);
			
		}
	}
	
	
	/**
	 * M�todo que realiza a pintura do grid, exibe o Score do game 
	 * na tela e pinta a pe�a atual controlada pelo jogador.
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
		
		// Pinta no grid a pe�a atualmente ativa pelo jogador.
		drawPieceOnGrid(g);
		
	}
	
	
	/**
	 * M�todo respons�vel por fazer a pe�a parte do grid. Quando uma pe�a 
	 * atinge o "ch�o" do grid, ou alguma outra pe�a, esta pe�a controlada 
	 * pelo jogador ser� "grudada" e far� parte do grid com as coordenadas
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
	 * @return Retorna true se as pe�as atingiram o topo do game, ou false caso contr�rio.
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
	 * Deleta uma linha dentro do grid indicada por par�metro.
	 * Caso houver pe�as acima desta linha, todas as pe�as localizadas 
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
	 * estas linhas ser�o deletadas e o jogador pontua de acordo 
	 * com o n�mero de linhas que ser�o deletadas. Neste caso,
	 * o m�ximo poss�vel s�o 4 linhas (Pe�a com maior tamanho
	 * vertical - Pe�a I)
	 * 
	 * @return True possui linha cheia. False caso contr�rio.
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
	 * M�todo para derrubar no grid a pe�a atual. Ela adquire as 
	 * coordenadas X e Y atuais da pe�a, e verifica se ela ainda pode
	 * manter caindo verificando se possui colis�o. Caso negativo, ela
	 * ir� cair uma posi��o, caso contr�rio ela ser� fixada no grid.
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
	 * Para uma pe�a de continuar caindo dentro do grid.
	 */
	public void stopDropping(){
		actualPiece.stopPiece();
	}
	
	
	/**
	 * Rotaciona uma pe�a dentro do grid. Recebe a pe�a atual controlada pelo jogador
	 * e chama o m�todo que executa de fato a rota��o da pe�a. Localizada na classe Piece.
	 * Ap�s realizada a rota��o, a pe�a � repintada no grid.
	 * 
	 * @param r Recebe um valor r que determinar� a rota��o.
	 */
	public void rotate(int r){
		actualPiece.rotatePiece(r);
		pieceRotation = actualPiece.getRotation();
		repaint();
	}
	
	
	/**
	 * M�todo respons�vel por mover uma pe�a e repintar sua nova posi��o dentro do grid.
	 * A partir da pe�a atual controlada pelo jogador, este m�todo ser� executado quando
	 * o jogador desejar mover a pe�a para esquerda ou direita. A pe�a atual chama o m�todo
	 * movePiece(int d) implementada na classe Piece. 
	 * 
	 * @param d Recebe um inteiro que representar� o deslocamento que a pe�a far� dentro do grid.
	 */
	public void moveLeftOrRight(int d){
		actualPiece.movePiece(d);
		repaint();
	}
	
	
	
    /**
     * M�todo para verificar se uma pe�a colide com algo. Dentre
     * as op��es pode ser os limites do grid, ou outras pe�as.
     * 
     * @param x Coordenada X atual da pe�a.
     * @param y Coordenada Y atual da pe�a.
     * @param rotation Rota��o atual da pe�a.
     * @return Retorna true caso haja colis�o poss�vel na proxima posi��o. 
     * 		   Caso contr�rio retornar� false.
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
	 * Retorna o n�mero de linhas do grid.
	 * 
	 * @return Retorna o n�mero de linhas.
	 */
	public int getROWS(){
		return ROWS;
	}
	
	
	
	/**
	 * Retorna o n�mero de colunas do grid.
	 * 
	 * @return Retorna o n�mero de colunas.
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

