package domainClassFunctional;


import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;

/**
 * CLASSE PIECET FUNCIONAL
 * 
 * Pe�a T. 
 *
 */
public class PieceT extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
	//Armazena o formato da pe�a.
	private Point[][] pieceT;
	
	//Indica como � o formato para armazenar ap�s em PieceT.
	private Point[][] pieceFormat =		
			{
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
			};
		
		
	/**
	 * Construtor de PieceT.
	 */
	public PieceT(){
		pieceLocation = new Point(5, 1);
		setPiece(pieceFormat); 	
	}


	/**
	 * @return the PieceT
	 */
	public Point[][] getPiece() {
		return pieceTF.getPiece();
	}


	/**
	 * @param PieceT the PieceT to set
	 */
	public void setPiece(Point[][] pieceT) {
		changePieceT.setPiece(pieceT);
	}

	
	/**
	 * Retorna as coordenadas da pe�a.
	 */
	@Override
	public Point[][] getPieceCoordinates() {
		return takeCoordinates.getPieceCoordinates();
	}
	

	// ***** PARTE FUNCIONAL - CLASSE PIECE T *****

	SetPiece changePieceT = (Point[][] p) -> {pieceT = p ;}; 
	GetPiece pieceTF = () -> {return pieceT;};
	GetPieceCoordinates takeCoordinates = () -> {return pieceT;};
	

		
}
