package domainClassFunctional;

import functionalInterfaces.GetPiece;
import functionalInterfaces.GetPieceCoordinates;
import functionalInterfaces.SetPiece;



/**
 * CLASSE PIECEL FUNCIONAL
 * 
 * Peça L
 *
 */
public class PieceL extends Piece  implements SetPiece, GetPiece, GetPieceCoordinates {
	
			//Armazena o formato da peça.
			private Point[][] pieceL;
			
			//Indica como é o formato para armazenar após em PieceL.
			private Point[][] pieceFormat =		
					{
						{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
						{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
						{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
						{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) }
					};
				
			/**
			 * Construtor de PieceL.
			 */
			public PieceL(){
				pieceLocation = new Point(5, 1);
				setPiece(pieceFormat); 	
			}


			/**
			 * @return the PieceL
			 */
			public Point[][] getPiece() {
				return pieceLF.getPiece();
			}


			/**
			 * @param PieceL the PieceL to set
			 */
			public void setPiece(Point[][] pieceL) {
				changePieceL.setPiece(pieceL);
			}

			
			/**
			 * Retorna as coordenadas da peça.
			 */
			@Override
			public Point[][] getPieceCoordinates() {
				return takeCoordinates.getPieceCoordinates();
			}
			
			
			// ***** PARTE FUNCIONAL - CLASSE PIECE L *****

			
			SetPiece changePieceL = (Point[][] p) -> {pieceL = p ;}; 
			GetPiece pieceLF = () -> {return pieceL;};
			GetPieceCoordinates takeCoordinates = () -> {return pieceL;};
			

				
}
