/**
	* <p class="sessionCustom">Description:</p>
	* <p>Clase que controla la lectura de archivos y direcciones</p>
	* @author Alvaro Cordoba
	* @since 17/02/16
	* @version 1.0
*/

package conceptosAvanzados.lectores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conceptosAvanzados.estructuras.Plantilla;
import conceptosAvanzados.constantes.Constantes;

public final class Textos
{
	
	private Pattern p;
	private Matcher m;
	private String linea;
/**
	Descripcion del metodo, clases, interfaces
	@param parametros de entrada metodo
	@return variable de retorno de metodos
	@throws exceptions que lanza el metodo o clase
*/
	
	public Textos()
	{
		p = null;
		m = null;
		linea = null;	
	}
	
	public void leerArchivosJava(File f, Plantilla estructuraProyecto)
	{
		FileReader fr = null;
		BufferedReader br = null;
		
		try
		{
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			linea = br.readLine();
			Plantilla claseTemporal = null;
			Plantilla interfaceTemporal = null;
			Plantilla metodoTemporal = null;
			int contadorCorchetesClase = 0;
			int contadorCorchetesInterfaces = 0;
			int contadorCorchetesMetodos = 0;
			boolean esEstructuraClase = false;
			boolean esEstructuraInterface = false;
			boolean esEstructuraMetodos = false;
						
			while (linea != null)
			{
				if (!validarLineaPatrones(Constantes.COMENTARIOS) || !validarLineaPatrones(Constantes.OTROS))
				{
					linea = br.readLine();
					continue;
				}				
				else if (!esEstructuraClase && validarLineas(Constantes.CLASES))
				{
					claseTemporal = estructuraProyecto.agregarSubObjecto(obtenerNombre(Constantes.NOMBRE_CLASES),Constantes.CLASE);
					claseTemporal.IncremetrarNumeroLienas();
					esEstructuraClase = true;
				}
				else if(!esEstructuraInterface && validarLineas(Constantes.INTERFACES))
				{	
					interfaceTemporal = estructuraProyecto.agregarSubObjecto(obtenerNombre(Constantes.NOMBRE_INTERFACES), Constantes.INTERFACE);
					interfaceTemporal.IncremetrarNumeroLienas();
					esEstructuraInterface = true;									
				}
				else if (!esEstructuraMetodos && validarLineas(Constantes.METODOS) && !linea.contains("while"))
				{
					if(esEstructuraClase)
					{
						metodoTemporal = claseTemporal.agregarSubObjecto(obtenerNombre(Constantes.NOMBRE_METODOS), Constantes.METODO);
						metodoTemporal.IncremetrarNumeroLienas();
						esEstructuraMetodos = true;
					}
					else if(esEstructuraInterface)
					{
						metodoTemporal = interfaceTemporal.agregarSubObjecto(obtenerNombre(Constantes.NOMBRE_METODOS), Constantes.METODO);
						metodoTemporal.IncremetrarNumeroLienas();
					}
				}
				else if(esEstructuraInterface && !esEstructuraMetodos && validarLineas(Constantes.LINEAS))
				{
					interfaceTemporal.IncremetrarNumeroLienas();
				}
				else if(esEstructuraClase && !esEstructuraMetodos && validarLineas(Constantes.LINEAS))
				{
					claseTemporal.IncremetrarNumeroLienas();
				}
				else if(esEstructuraMetodos && validarLineas(Constantes.LINEAS))
				{			
					metodoTemporal.IncremetrarNumeroLienas();
				}
				else if (esEstructuraMetodos)
				{
					if(validarLineas(Constantes.CORCHETE_ABIERTO))
						contadorCorchetesMetodos++;
					else if(validarLineas(Constantes.CORCHETE_CERRADO))
						contadorCorchetesMetodos--;
						
					if(contadorCorchetesMetodos == 0)
						esEstructuraMetodos = false;
										
					linea = br.readLine();
					continue;
				}
				else if (esEstructuraInterface)
				{				
					if(validarLineas(Constantes.CORCHETE_ABIERTO))
						contadorCorchetesInterfaces++;
					else if(validarLineas(Constantes.CORCHETE_CERRADO))
						contadorCorchetesInterfaces--;
						
					if(contadorCorchetesInterfaces == 0)
						esEstructuraInterface = false;
									
					linea = br.readLine();
					continue;
				}
				else if (esEstructuraClase)
				{
					if(validarLineas(Constantes.CORCHETE_ABIERTO))
						contadorCorchetesClase++;
					else if(validarLineas(Constantes.CORCHETE_CERRADO))
						contadorCorchetesClase--;
						
					if(contadorCorchetesClase == 0)
						esEstructuraClase = false;
					
					linea = br.readLine();
					continue;
				}
				
				linea = br.readLine();
			}
			
			br.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			System.exit(0);
		}
	}

	private boolean validarLineas(String patron)
	{
		
		boolean seCuentaLinea = false;
	
		p = Pattern.compile(patron);
		m = p.matcher(linea);
	
		while (m.find())
		{
			seCuentaLinea = true;			
			break;
		}
		
		return seCuentaLinea;
	}
	
	private boolean validarLineaPatrones(ArrayList<String> patrones)
	{
		
		boolean seCuentaLinea = true;
		
		for(String expresion : patrones)
		{			
			p = Pattern.compile(expresion);
			m = p.matcher(linea);

			while (m.find())
			{
				seCuentaLinea = false;			
				break;
			}
			
			if(!seCuentaLinea)
				break;
		}
		
		return seCuentaLinea;
	}

	private String obtenerNombre(String patron)
	{
			
		p = Pattern.compile(patron);
		m = p.matcher(linea);
	
		while (m.find())
		{
			return m.group(1);
		}
		
		System.out.println("No se pudo obtener el nombre del objeto");
		System.exit(4);
		return null;
		
	}
	
}