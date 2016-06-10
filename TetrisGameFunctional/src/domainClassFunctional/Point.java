package domainClassFunctional;


/**
 * CLASSE POINT FUNCIONAL
 * 
 * Classe Ponto. Esta classe ser� a respons�vel por criar os pontos dentro da coordenada
 * x e y no grid. Os pontos neste caso, corresponder�o as pe�as do game. Cada pe�a, ser� 
 * formada por diversos pontos. Por exemplo, a pe�a N poderia ser representada da seguinte 
 * forma:
 * 		  *
 *  	 **
 *		 *
 */
public class Point {
	
	
	//Atributos para configurar as coordenadas do eixo X e Y.
	private int positionX, positionY;
	
	
	
	/**
	 * Construtor Padr�o da classe Ponto. Inicializa
	 * com as coordenadas x e y em 0.
	 * 
	 * @param x Coordenada do eixo X
	 * @param y Coordenada do eixo Y
	 */
	public Point (){
		positionX = 0;
		positionY = 0;
	}
	
	
	/**
	 * Construtor parametrizado da classe Ponto.
	 * 
	 * @param x Coordenada do eixo X
	 * @param y Coordenada do eixo Y
	 */
	public Point (int x, int y){
		positionX = x;
		positionY = y;
	}
	
	
	/**
	 * @return A PositionX
	 */
	public int getPositionX() {
		return takeX.getPositionX();
	}
	
	/**
	 * @return A positionY
	 */
	public int getPositionY() {
		return takeY.getPositionY();
	}


	/**
	 * Modifica a posi��o X do ponto.
	 * 
	 * @param positionX 
	 */
	public void setPositionX(int positionX) {
		changeX.setPositionX(positionX);
	}
	
	
	/**
	 * Modifica a posi��o Y do ponto.
	 *  
	 * @param positionY 
	 */
	public void setPositionY(int positionY) {
		changeY.setPositionY(positionY);
	}
	
	
	
	// ***** PARTE FUNCIONAL - CLASSE POINT *****
	
	
	SetPositionX changeX = (int pX) -> {positionX = pX;};
	SetPositionY changeY = (int pY) -> {positionY = pY;};
	GetPositionX takeX = ()-> {return positionX;};
	GetPositionY takeY = ()-> {return positionY;};

	
	interface SetPositionX{
		void setPositionX(int posX);
	}
	
	interface SetPositionY{
		void setPositionY(int posY);
	}
	
	interface GetPositionX{
		int getPositionX();
	}
	
	interface GetPositionY{
		int getPositionY();
	}
	
}

