package es.aguasnegras.bbReader.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;
import es.aguasnegras.bbReader.R;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.entidades.PaginaForos;

public class PaginaForosAdapter {

	private PaginaForos paginaForos = null;
	private Context contexto = null;
	private SimpleAdapter adaptador = null;

	public PaginaForosAdapter(Context contexto, PaginaForos paginaForos) {
		this.contexto = contexto;
		this.paginaForos = paginaForos;
	}

	public PaginaForos getPaginaForos() {
		return paginaForos;
	}

	public void setPaginaForos(PaginaForos paginaForos) {
		this.paginaForos = paginaForos;
	}

	public Context getContexto() {
		return contexto;
	}

	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}

	public SimpleAdapter getAdaptador() {
		if (this.adaptador == null) {
			this.generarAdaptador();
		}
		return adaptador;
	}

	public void setAdaptador(SimpleAdapter adaptador) {
		this.adaptador = adaptador;
	}

	private void generarAdaptador() {
		String[] from = { "nombreForo" };
		int[] to = { R.id.filaForo };
		List<Map<String, String>> valoresFilas = new ArrayList<Map<String, String>>();
		for (Foro foro : this.paginaForos.getForos()) {
			Map<String, String> filaForo = new HashMap<String, String>();
			filaForo.put("nombreForo", foro.getTitulo());
			valoresFilas.add(filaForo);
		}
		this.adaptador = new SimpleAdapter(this.contexto, valoresFilas,
				R.layout.fila_foro, from, to);
	}

}
