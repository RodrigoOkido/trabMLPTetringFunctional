package domainClassFunctional;


import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;


/**
 * CLASSE PIECEN FUNCIONAL
 * 
 * Pe�a N. 
 *
 */
public class PieceN extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da pe�a.
			private Point[][] pieceN;
			
			//Indica como � o formato para armazenar ap�s em PieceL.
			private Point[][] pieceFormat =		
					{
						{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
						{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
						{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
						{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }
					};
				
			/**
			 * Construtor de PieceN.
			 */
			public PieceN(){
				pieceLocation = new Point(5, 1);
				setPiece(pieceFormat); 	
			}


			/**
			 * @return the PieceN
			 */
			public Point[][] getPiece() {
				return pieceNF.getPiece();
			}


			/**
			 * @param PieceN the PieceN to set
			 */
			public void setPiece(Point[][] pieceN) {
				changePieceN.setPiece(pieceN);
			}

			
			/**
			 * Retorna as coordenadas da pe�a.
			 */
			@Override
			public Point[][] getPieceCoordinates() {
				return takeCoordinates.getPieceCoordinates();
			}
			

			// ***** PARTE FUNCIONAL - CLASSE PIECE N *****
			
			SetPiece changePieceN = (Point[][] p) -> {pieceN = p ;}; 
			GetPiece pieceNF = () -> {return pieceN;};
			GetPieceCoordinates takeCoordinates = () -> {return pieceN;};
			

				
}

