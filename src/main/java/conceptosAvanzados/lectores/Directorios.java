package conceptosAvanzados.lectores;
/**
* <p class="sessionCustom">Description:</p>
* <p>Retorna direccion de carpetas</p>
* @author Alvaro Cordoba
* @since 17/02/16
* @version 1.0
*/


import javax.swing.JFileChooser;

public final class Directorios
{

	public String Leer()
	{
		String direccion;
		javax.swing.JFileChooser j = new javax.swing.JFileChooser();
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		j.showOpenDialog(j);

		direccion = j.getSelectedFile().getAbsolutePath();
		if(direccion.equals(null) || direccion.equals(""))
		{
			javax.swing.JOptionPane.showMessageDialog(j, "Ha finalizado el Programa");
			System.exit(0);
		}
		
		return direccion;
	}

}
