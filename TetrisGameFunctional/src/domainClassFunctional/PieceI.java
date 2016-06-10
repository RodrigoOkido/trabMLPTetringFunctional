package domainClassFunctional;

import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;

/**
 * CLASSE PIECEI FUNCIONAL
 * 
 * Pe�a I. 
 *
 */
public class PieceI extends Piece implements SetPiece, GetPiece, GetPieceCoordinates{
	
	//Armazena o formato da pe�a.
	private Point[][] pieceI;
	
	//Indica como � o formato para armazenar ap�s em PieceI.
	private Point[][] pieceFormat =		
			{
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
			};
		
		
	/**
	 * Construtor de PieceI.
	 */
	public PieceI(){
		pieceLocation = new Point(5, 1);
		setPiece(pieceFormat); 	
	}


	/**
	 * @return the pieceI
	 */
	public Point[][] getPiece() {
		return pieceIF.getPiece();
	}


	/**
	 * @param pieceI the pieceI to set
	 */
	public void setPiece(Point[][] pieceI) {
		changePieceI.setPiece(pieceI);
	}

	
	/**
	 * Retorna as coordenadas da pe�a.
	 */
	@Override
	public Point[][] getPieceCoordinates() {
		return takeCoordinates.getPieceCoordinates();
	}
	

	// ***** PARTE FUNCIONAL - CLASSE PIECE I *****
	
	SetPiece changePieceI = (Point[][] p) -> {pieceI = p ;}; 
	GetPiece pieceIF = () -> {return pieceI;};
	GetPieceCoordinates takeCoordinates = () -> {return pieceI;};

}

