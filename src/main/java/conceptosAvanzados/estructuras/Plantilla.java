package conceptosAvanzados.estructuras;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import conceptosAvanzados.constantes.Constantes;

public class Plantilla implements Map<Integer, Plantilla>
{
	private static int codigoObjecto = 0;
	private String nombre;
	private int identificadorObjecto;
	private static int numeroTotalLineas = 0;
	private int tipoObjeto;
	private long numeroLineas;
	private int indiceMapa;
	private int nivelObjeto;
	private HashMap<Integer, Plantilla> mapaObjectos = new HashMap<Integer, Plantilla>();
	
	public int getNumeroTotalLineas() {
		return numeroTotalLineas;
	}

	public boolean isCarpeta()
	{
		if(tipoObjeto == Constantes.CARPETA)
			return true;
		
		return false;
	}
	
	public boolean isClase()
	{
		if(tipoObjeto == Constantes.CLASE)
			return true;
		
		return false;
	}
	public boolean isInterface()
	{
		if(tipoObjeto == Constantes.INTERFACE)
			return true;
		
		return false;
	}
	public boolean isMetodo()
	{
		if(tipoObjeto == Constantes.METODO)
			return true;
		
		return false;
	}
		
	public int obtenerTipoObjeto()
	{
		return tipoObjeto;
	}
	
	public Plantilla(String nombre, int tipoObjeto)
	{
		super();
		indiceMapa = 0;
		this.nombre = nombre;
		this.tipoObjeto = tipoObjeto;
		numeroLineas = 0;
		identificadorObjecto = codigoObjecto++;
	}	
		
	public boolean isRaiz() {
		if(identificadorObjecto == 0)
			return true;
		return false;
		
	}
	
	
	
	public long getNumeroLineas()
	{
		return numeroLineas;
	}
	
	public Plantilla agregarSubObjecto(String nombre, int tipoObjeto)
	{
		Plantilla objectoTemporal = new Plantilla(nombre, tipoObjeto);
		mapaObjectos.put(indiceMapa, objectoTemporal);
		indiceMapa++;
		return objectoTemporal;
	}
	
	public void IncremetrarNumeroLienas()
	{		
		numeroLineas++;
		numeroTotalLineas++;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return tipoObjeto;
	}
	
	@Override
	public void clear()
	{
		mapaObjectos.clear();		
	}

	@Override
	public boolean containsKey(Object key) 
	{
		return mapaObjectos.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return mapaObjectos.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Integer, Plantilla>> entrySet() 
	{
		return mapaObjectos.entrySet();
	}

	@Override
	public Plantilla get(Object key)
	{
		return mapaObjectos.get(key);
	}

	@Override
	public boolean isEmpty() 
	{
		return mapaObjectos.isEmpty();
	}

	@Override
	public Set<Integer> keySet() 
	{
		return mapaObjectos.keySet();
	}

	@Override
	public Plantilla put(Integer key, Plantilla value) 
	{
		return mapaObjectos.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Plantilla> m)
	{
		mapaObjectos.putAll(m);
	}

	@Override
	public Plantilla remove(Object key) 
	{
		return mapaObjectos.remove(key);
	}

	@Override
	public int size() 
	{
		return mapaObjectos.size();
	}
	
	@Override
	public Collection<Plantilla> values()
	{
		return mapaObjectos.values();
	}	
	
	@Override
	public String toString() {
		
		String texto = "";
		
		if(tipoObjeto == Constantes.CLASE)
			texto += "Clase:";
		else if(tipoObjeto == Constantes.CARPETA)
			texto += "Carpeta:";
		else if(tipoObjeto == Constantes.INTERFACE)
			texto += "Interface:";
		else if(tipoObjeto == Constantes.METODO)
			texto += "Metodo:";
		texto += " " + nombre;
		
		if(tipoObjeto != Constantes.CARPETA )
		{
			texto += " , numero lineas: " + numeroLineas;
		}
		
		return texto;
	}

	public int getNivelObjeto() {
		return nivelObjeto;
	}

	public void setNivelObjeto(int nivelObjeto) {
		this.nivelObjeto = nivelObjeto;
	}
	
}
