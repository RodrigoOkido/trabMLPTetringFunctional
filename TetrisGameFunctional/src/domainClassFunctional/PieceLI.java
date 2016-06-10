package domainClassFunctional;

import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;


/**
 * CLASSE PIECELI FUNCIONAL
 * 
 * Pe�a L Invertida. 
 *
 */
public class PieceLI extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da pe�a.
			private Point[][] pieceLI;
			
			//Indica como � o formato para armazenar ap�s em PieceLI.
			private Point[][] pieceFormat =		
					{
						{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
						{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
						{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
						{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) }
					};
				
			/**
			 * Construtor de PieceLI.
			 */
			public PieceLI(){
				pieceLocation = new Point(5, 1);
				setPiece(pieceFormat); 	
			}


			/**
			 * @return the PieceLI
			 */
			public Point[][] getPiece() {
				return pieceLIF.getPiece();
			}


			/**
			 * @param PieceLI the PieceLI to set
			 */
			public void setPiece(Point[][] pieceLI) {
				changePieceLI.setPiece(pieceLI);
			}

			
			/**
			 * Retorna as coordenadas da pe�a.
			 */
			@Override
			public Point[][] getPieceCoordinates() {
				return takeCoordinates.getPieceCoordinates();
			}
			
			
			
			//  ***** PARTE FUNCIONAL - CLASSE PIECE LI  *****

			SetPiece changePieceLI = (Point[][] p) -> {pieceLI = p ;}; 
			GetPiece pieceLIF = () -> {return pieceLI;};
			GetPieceCoordinates takeCoordinates = () -> {return pieceLI;};
			

				
}



