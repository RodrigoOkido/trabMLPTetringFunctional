package domainClassFunctional;

import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;


/**
 * CLASSE PIECELI FUNCIONAL
 * 
 * Peça L Invertida. 
 *
 */
public class PieceLI extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da peça.
			private Point[][] pieceLI;
			
			//Indica como é o formato para armazenar após em PieceLI.
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
			 * Retorna as coordenadas da peça.
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



