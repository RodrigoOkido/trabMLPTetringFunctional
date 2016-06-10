package Interfaces;

/**
 * Classe para associar alguma lista qualquer Genérica.
 * Neste caso, o propósito será usar esta interface para a lista de Peças do game. (Classe PieceCollection)
 * 
 * 
 * @param <E> Especifica o tipo
 */
public interface Collection<E> {
	
	/**
	 * Retorna um vetor de elementos de algum tipo.
	 * @return Retorna esta lista
	 */
	public E[] getList();
	
	
	/**
	 * Associa uma lista de elementos a alguma outra lista.
	 
	 * @param list Recebe uma lista a ser associada.
	 * @return Retorna true se a lista for possível associar, ou false caso a lista recebida por parâmetro
	 * 			 seja maior que o tamanho da lista que receberá essa lista.
	 */
	public boolean associateList(E[] list);

}
