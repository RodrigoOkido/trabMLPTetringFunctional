package domainClassFunctional;

import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;


/**
 * CLASSE PIECESQUARE FUNCIONAL
 * 
 * Pe�a Square. 
 *
 */
public class PieceSquare extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da pe�a.
			private Point[][] pieceSquare;
			
			//Indica como � o formato para armazenar ap�s em PieceSquare.
			private Point[][] pieceFormat =		
				{
						{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
						{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
						{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
						{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }
					};
				
				
			/**
			 * Construtor de PieceSquare.
			 */
			public PieceSquare(){
				pieceLocation = new Point(5, 1);
				setPiece(pieceFormat); 	
			}


			/**
			 * @return the PieceN
			 */
			public Point[][] getPiece() {
				return pieceSquareF.getPiece();
			}


			/**
			 * @param PieceN the PieceN to set
			 */
			public void setPiece(Point[][] pieceSquare) {
				changePieceSquare.setPiece(pieceSquare);
			}

			
			/**
			 * Retorna as coordenadas da pe�a.
			 */
			@Override
			public Point[][] getPieceCoordinates() {
				return takeCoordinates.getPieceCoordinates();
			}
			

			// ***** PARTE FUNCIONAL - CLASSE PIECE SQUARE *****
	
			SetPiece changePieceSquare = (Point[][] p) -> {pieceSquare = p ;}; 
			GetPiece pieceSquareF = () -> {return pieceSquare;};
			GetPieceCoordinates takeCoordinates = () -> {return pieceSquare;};
			

				
}
