package conceptosAvanzados.constantes;

import java.util.ArrayList;

public class Constantes
{

	public static final String DIRECTORIO_RAIZ = "src";
	public static final int CARPETA = 0;
	public static final int CLASE = 1;
	public static final int INTERFACE = 2;
	public static final int METODO = 3;
	public static final String NOMBRE_CLASES = "class\\s+(.+)(\\s+)?$";
	public static final String NOMBRE_INTERFACES = "interface\\s+(.+)(\\s+)?$";
	public static final String NOMBRE_METODOS = "((\\w(?![\\s])+(\\d+(?![\\s]))?)+)\\(";
	public static final String METODOS = "^(\\s+)?(((abstract|synchronized|private|protected|public|final|static)+)?(\\D(?![=.]))+)\\)(\\s+|;)?$"; // Validacion Metodos
	public static final String CLASES = "^(\\s+)?(((abstract|synchronized|private|protected|public|final|static)\\s+)+)?(\\s+)?class\\s+\\w+(\\s+)?(\\{)?$"; // Validacion Classes
	public static final String INTERFACES = "^(\\s+)?(public)?(\\s+)?interface\\s+\\w+(\\s+)?$"; // Validacion interface
	public static final String LINEAS = "^(\\s+)?(\\*\\/)?(((continue|for|switch|boolean|do|if|private|this|break|double|protected|byte|else|public|case|enum|return|catch|int|short|try|char|final|static|finally|long|float|super|while).+)|((([^0-9\\{\\}]+|(.+\\=.+)|(.+\\..+)))))(\\;|\"|\\:|\\+)?(\\/\\/|\\/\\*)?(\\s+)?$"; // Validacion Lineas
	public static final String CORCHETE_ABIERTO = "^(\\s+)?(\\{)(\\s+)?$"; //
	public static final String CORCHETE_CERRADO = "^(\\s+)?(\\})(\\s+)?$"; //
	public static ArrayList<String> COMENTARIOS = new ArrayList<String>()
	{
		private static final long serialVersionUID = 1L;

		{
			add("^(\\s+)?\\/\\*{2}(.+)?(\\s+)?$"); // Validacion /** Comentarios
			add("^(\\s+)?\\*(\\s+|.+)?$"); // Validacion * Comentarios
			add("^(\\s+)?\\*\\/(\\s+)?$"); // Validacion */ Comentarios
			add("^(\\s+)?$"); // Validacion Espacio en Blanco
			add("^(\\s+)?(\\/\\/)(.+)?(\\s+)?$"); // Validacion //
		}
	};

	public static ArrayList<String> OTROS = new ArrayList<String>()
	{
		private static final long serialVersionUID = 1L;

		{
			add("^(\\s+)?(@\\w+)((\\s+)?(\\w+))+?(\\s+)?$"); // etiqueta @
			add("^(\\s+)?(import|package)\\D+(;)(\\s+)?$"); // Validacion import y pacakage
		}
	};

}