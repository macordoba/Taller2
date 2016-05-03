/**
	* <p class="sessionCustom">Description:</p>
	* <p>Clase que controla la seleccion de carpertas y proceso de conteo</p>
	* @author Alvaro Cordoba
	* @since 17/02/16
	* @version 1.0
*/

package conceptosAvanzados;

import java.io.File;
import java.util.ArrayList;

import conceptosAvanzados.constantes.Constantes;
import conceptosAvanzados.estructuras.*;
import conceptosAvanzados.lectores.Directorios;
import conceptosAvanzados.lectores.Textos;
import static conceptosAvanzados.utilidades.Utilidades.imprimirResultado;

public class ContadorLineas {

	public static void main(String[] args) 
	{	
		Plantilla proyecto = null;
		boolean esDirectorioCorrecto = false;
		boolean termino = true; 
		File directorioRaiz = new File(new Directorios().Leer());
		ArrayList<File> carpetasDirectorio = new  ArrayList<File>();
		
		for(File buscador : directorioRaiz.listFiles())
		{
			if(!buscador.isFile() && buscador.getName().equals(Constantes.DIRECTORIO_RAIZ))
			{
				proyecto = new Plantilla(directorioRaiz.getName(), Constantes.CARPETA);
				directorioRaiz = buscador;
				esDirectorioCorrecto = true;
				break;
			}
		}
		
		if(!esDirectorioCorrecto)
		{
			System.out.println("El directorio seleccionado no contiene carpeta src");
			System.exit(0);
		};
		
		int indeceFiles = 1;
		int indicellenado = 0;
		int indiceCarpetas = 0;
		carpetasDirectorio.add(directorioRaiz);
		ArrayList<Plantilla> carpetasTemporal = new ArrayList<Plantilla>();
		carpetasTemporal.add(indicellenado, proyecto);
		do {
		
		for(File directorio : directorioRaiz.listFiles())
		{
			if(!directorio.isFile())
			{
				carpetasDirectorio.add(indeceFiles++, directorio);
				carpetasTemporal.add(indicellenado+1, carpetasTemporal.get(indiceCarpetas).agregarSubObjecto(directorio.getName(), Constantes.CARPETA));
				indicellenado++;
			}
			else
			{
				if(indicellenado==0)
					new Textos().leerArchivosJava(directorio, carpetasTemporal.get(indicellenado));
				else
					new Textos().leerArchivosJava(directorio, carpetasTemporal.get(indicellenado-1));
			}
			
		}
		
		if (carpetasDirectorio.size()-1 > indiceCarpetas) {
			directorioRaiz = carpetasDirectorio.get(++indiceCarpetas);
		
		}
		else
		{
			termino = false;
		}
		
		}while(termino);

	imprimirResultado(proyecto);
	}
}