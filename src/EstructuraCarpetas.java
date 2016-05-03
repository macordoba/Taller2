/**
* <p class="sessionCustom">Description:</p>
* <p>Estructura que almacena de forma ordenada la informacion las carpetas</p>
* @author Alvaro Cordoba
* @since 17/02/16
* @version 1.0
*/

package conceptosAvanzados.EstructuraDatos;

import java.util.ArrayList;

public final class  EstructuraCarpetas
{
	private ArrayList<EstructuraClases> clases;
	private static int identificadorCarpeta = 0; 
	
	//Prueba Texto
	
	EstructuraCarpetas()
	{
		clases =  new ArrayList<EstructuraClases>();
		identificadorCarpeta++; // Aumenta 
	}
	
	void valeMia()
	{
		int valor = 3;		
	}
}

interface valemia 
{
		String suma(int valor, int valor);
		void llamar();
}