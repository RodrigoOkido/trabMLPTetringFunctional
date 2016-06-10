package domainClassFunctional;


import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;


/**
 * CLASSE PIECENI FUNCIONAL
 * 
 * Pe�a N Invertida. 
 *
 */
public class PieceNI extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da pe�a.
			private Point[][] pieceNI;
			
			//Indica como � o formato para armazenar ap�s em PieceNI.
			private Point[][] pieceFormat =		
					{
						{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
						{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
						{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
						{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }
					};
				
			/**
			 * Construtor de PieceNI.
			 */
			public PieceNI(){
				pieceLocation = new Point(5, 1);
				setPiece(pieceFormat); 	
			}


			/**
			 * @return the PieceNI
			 */
			public Point[][] getPiece() {
				return pieceNIF.getPiece();
			}


			/**
			 * @param PieceNI the PieceNI to set
			 */
			public void setPiece(Point[][] pieceNI) {
				changePieceNI.setPiece(pieceNI);
			}

			
			/**
			 * Retorna as coordenadas da pe�a.
			 */
			@Override
			public Point[][] getPieceCoordinates() {
				return takeCoordinates.getPieceCoordinates();
			}
			

			// ***** PARTE FUNCIONAL - CLASSE PIECE NI *****

			SetPiece changePieceNI = (Point[][] p) -> {pieceNI = p ;}; 
			GetPiece pieceNIF = () -> {return pieceNI;};
			GetPieceCoordinates takeCoordinates = () -> {return pieceNI;};
			

				
}
