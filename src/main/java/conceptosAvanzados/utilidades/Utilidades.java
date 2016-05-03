package conceptosAvanzados.utilidades;

import java.util.ArrayList;
import conceptosAvanzados.estructuras.Plantilla;

public class Utilidades {

	public static final void imprimirResultado(Plantilla raiz) {
		boolean completo;
		int indicePlantilla = 1;
		int indeceTexto = 0;
		ArrayList<Plantilla> temporalPlantillas = new ArrayList<Plantilla>();
		ArrayList<String> textos = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String toString() {
				String texto = "";

				for (int i = 0; i < this.size(); i++) {
					texto += this.get(i).toString();
					if (i == this.size() - 1)
						texto += "  ";
					else
						texto += " - ";

				}
				return texto;
			}
		};

		Plantilla temporalPlantilla = raiz;

		temporalPlantillas.add(raiz);
		int indicellenado = 1;
		int indiceNivel = 0;
		do {
			completo = false;
			indiceNivel++;
			
			for (Plantilla temporal : temporalPlantilla.values()) {
				temporal.setNivelObjeto(indiceNivel);		
				temporalPlantillas.add(indicellenado++, temporal);
				if (!temporal.values().isEmpty()) {
					completo = true;
				}
			}

			if (completo || indicePlantilla < temporalPlantillas.size()) {
				completo = true;
				indicellenado = indicePlantilla;
				indicellenado++;
				temporalPlantilla = temporalPlantillas.get(indicePlantilla++);
			}

		} while (completo);

		Plantilla objetoAnterior = null;

		for (Plantilla temporal : temporalPlantillas) {

			if (temporal.isCarpeta() && !temporal.isRaiz() && objetoAnterior == null) {
				textos.add(indeceTexto++, temporal.toString());
			}
			else if(objetoAnterior != null && objetoAnterior.getNivelObjeto() > temporal.getNivelObjeto())
			{
				int indiceObjetoAnterior = textos.size()-1;
				int indiceObjetoAcutal = temporal.getNivelObjeto();
				while (indiceObjetoAnterior >=  indiceObjetoAcutal)
				{
					textos.remove(indiceObjetoAnterior--);
					indeceTexto--;
				}				
			}
			else if (objetoAnterior != null && temporal.getNivelObjeto() == objetoAnterior.getNivelObjeto()) {
				textos.remove(--indeceTexto);
			} else if (objetoAnterior != null && objetoAnterior.isMetodo() && (temporal.isClase() || temporal.isInterface())) {
				int indice = indeceTexto - 1;
				boolean termino = true;
				do {
					if (textos.get(indice).contains("Clase") || textos.get(indice).contains("Interface")) {
						termino = false;
					}

					textos.remove(indice--);

				} while (termino);

				indeceTexto = indice + 1;
			}		
			else if (temporal.isMetodo()) {
				textos.set(indeceTexto - 1,
						textos.get(indeceTexto - 1).substring(0, textos.get(indeceTexto - 1).indexOf(",") - 1));
			}

			textos.add(indeceTexto++, temporal.toString());
			System.out.println(textos);
			objetoAnterior = temporal;
		}

		System.out.println("Numero total de lineas del programa: " + raiz.getNumeroTotalLineas());
	}

}
