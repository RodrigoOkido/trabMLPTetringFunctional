package domainClassFunctional;


import java.awt.Color;
import domainClassFunctional.Point;
import tetrisInterfaceFunctional.Grid;

public abstract class Piece {
	
		//Localização do Ponto da peça. Neste atributo é também definido 
		//onde a peça começará caindo dentro do grid.
		protected Point pieceLocation;
		
		//Rotação de uma peça. Começa na rotação Padrão.
		protected int rotation = 0;
			
		//Determinação de uma cor a peça
		protected static final Color[] pieceColors = {Color.BLUE,Color.YELLOW, Color.PINK, Color.GREEN
				,Color.RED, Color.ORANGE, Color.WHITE};
	
	
		
	public abstract Point[][] getPieceCoordinates();
	
	
	public static Color[] getPieceColors() {
		return takeColors.getPieceColors();
	}
	
	public void dropPiece(){
		dropDownPiece.dropPiece();
	}
	
	public Point getPieceLocation(){
		return pieceCoordinate.getPieceLocation();
	}
     
	public int getRotation(){
		return pieceRotation.getRotation();	   
	}
	
	public void setRotation(int newValue){		
		piece.setRotation(newValue);	   
	}
	
	public void rotatePiece(int r){
		rotatePiece.rotate(r);
	}
	  
	public void movePiece(int deslocamento) {
		move.movePiece(deslocamento);
	}
	
	/**
	 * Método para parar uma peça. Este método apenas mantém atualizando a peça 
	 * com a última posição Y que a peça tinha.
	 */
	public void stopPiece() {
		stop.stopPiece();	
	}
	
	
	
	// ***** PARTE FUNCIONAL - CLASSE ABSTRATA PIECE *****
	
	PieceRotation pieceRotation = () -> {return rotation;};
	
	ChangeRotation piece = (int r) -> {return rotation = r;};
	
	PieceCoordinate pieceCoordinate = () -> {return pieceLocation;};
	
	static PieceColors takeColors = () -> {return pieceColors;};
	
	DropPiece dropDownPiece = ()-> {pieceLocation.setPositionY(pieceLocation.getPositionY()+1);};

	RotatePiece rotatePiece = (int r) -> { 
		int newRotation = (rotation + r) % 4;
		if (newRotation < 0) {
			newRotation = 3;
		}
		if (!Grid.collidesAtSomething(getPieceLocation().getPositionX(), getPieceLocation().getPositionY(), newRotation)) {
			rotation = newRotation;
		}
	};
	
	
	MovePiece move = (int d) -> {
		if (!Grid.collidesAtSomething(getPieceLocation().getPositionX() + d, getPieceLocation().getPositionY(), rotation)) {
			getPieceLocation().setPositionX(getPieceLocation().getPositionX()+d);	
			}
	};
	
	StopPiece stop = ()->{
		pieceLocation.setPositionY(pieceLocation.getPositionY());	
	};
	
	interface PieceColors {
		Color[] getPieceColors();

	}
	
	interface DropPiece{
		void dropPiece();
	}
	
	interface PieceCoordinate {
		Point getPieceLocation();
	}
	
	interface PieceRotation {
		int getRotation();
	}
	
	interface ChangeRotation {
		int setRotation(int r);
	}
	
	interface RotatePiece {
		void rotate(int r);
	}
	
	interface MovePiece {
		void movePiece(int d);
	}
	
	interface StopPiece{
		void stopPiece();
	}
	
}
