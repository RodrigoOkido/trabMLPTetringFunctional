package domainClassFunctional;

import Interfaces.Collection;
import domainClassFunctional.Piece;
import domainClassFunctional.PieceI;
import domainClassFunctional.PieceL;
import domainClassFunctional.PieceLI;
import domainClassFunctional.PieceN;
import domainClassFunctional.PieceNI;
import domainClassFunctional.PieceSquare;
import domainClassFunctional.PieceT;

/**
 * Classe que cria toda a lista de peças existentes do game.
 * Implementa a interface Collection, que generaliza receber 
 * qualquer tipo de lista para associar. Neste caso serão Peças.
 *
 */
public class PieceCollection implements Collection<Piece> {
	
	
	//numero de Peças caso
	private int pieceNumbers;
	
	
	//Vetor com todas as Peças do game.
	private Piece[] Pieces = {
			new PieceI(),
			new PieceL(),
			new PieceLI(),
			new PieceN(),
			new PieceNI(),
			new PieceSquare(),
			new PieceT()};
			
	//Vetor que recebe todas as peças.
	private Piece[] AllPieces;
	
	
	/**
	 * Construtor Padrão. Recebe o número de Peças
	 * padrão vindo da lista de peças. (Atributo Piece[] Pieces)
	 */
	public PieceCollection(){
		AllPieces = Pieces;
	}
	
	
	/**
	 * Construtor parametrizado de PieceCollection. 
	 * 
	 * @param n Recebe um numero que será o numero de peças.
	 */
	public PieceCollection(int n){
		pieceNumbers = n;
		AllPieces = new Piece[pieceNumbers];
	}
	
	
	/**
	 * Retorna o numero de peças da lista.
	 * 
	 * @return Retorna este numero.
	 */
	public int getPieceNumbers(){
		return checkPieceNumbers.getPieceNumber();
	}
	
	/**
	 * Modifica o numero de peças.
	 * 
	 * @param p Recebe um numero.
	 */
	public void setPieceNumbers(int p){
		changePieceNumbers.setPieceNumber(p);
	}
	
	
	/**
	 * Retorna o vetor com todas peças.
	 */
	public Piece[] getList(){
		return checkPieceList.getList();
	}
	
	/**
	 * Associa uma lista de peças a uma outra lista maior de peças. 
	 * Ideal para casos que é desejado aumentar o número de peças
	 * por exemplo.
	 * 
	 * @return Retorna true se a lista for maior que a lista rece-
	 * bida por parâmetro. False caso contrário.
	 */
	public boolean associateList(Piece[] list){
		return takelist.associateList(list);
			
	}
	
	
	// ***** PARTE FUNCIONAL - CLASSE PIECECOLLECTION *****
	
	GetPieceNumbers checkPieceNumbers = () -> {return pieceNumbers;};
	SetPieceNumbers changePieceNumbers = (int n) -> {pieceNumbers = n;};
	GetPieceList checkPieceList = () -> {return AllPieces;};
	AssociateList takelist = (Piece[] l) -> {
		if (pieceNumbers >= l.length){
		}
		for (int i = 0; i < Pieces.length; i++) {
			l[i] = AllPieces[i];
			return true;
		}
		
	
		return false;
		};
	
		
	interface GetPieceNumbers{
		int getPieceNumber();
	}
	
	interface SetPieceNumbers{
		void setPieceNumber(int n);
	}
	
	interface GetPieceList{
		Piece[] getList();
	}
	
	interface AssociateList{
		boolean associateList(Piece[] l);
	}
}
	
	


