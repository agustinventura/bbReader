package es.aguasnegras.bbReader.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.aguasnegras.bbReader.R;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.entidades.PaginaForos;

public class PaginaForosAdapter extends BaseAdapter {

	private PaginaForos paginaForos = null;
	private Context contexto = null;

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

	@Override
	public int getCount() {
		int numeroElementos = 0;
		if (this.paginaForos != null) {
			if (this.paginaForos.getForos() != null) {
				numeroElementos = this.paginaForos.getForos().size();
			}
		}
		return numeroElementos;
	}

	@Override
	public Object getItem(int posicion) {
		Object resultado = null;
		if (this.paginaForos != null && this.paginaForos.getForos() != null
				&& posicion >= 0
				&& posicion < this.paginaForos.getForos().size()) {
			resultado = this.paginaForos.getForos().get(posicion);
		}
		return resultado;
	}

	@Override
	public long getItemId(int posicion) {
		return posicion;
	}

	@Override
	public View getView(int posicion, View convertView, ViewGroup parentView) {
		LayoutInflater inflater = (LayoutInflater) contexto
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vistaFila = inflater
				.inflate(R.layout.fila_foro, parentView, false);
		TextView texto = (TextView) vistaFila.findViewById(R.id.filaForo);
		Object foro = this.getItem(posicion);
		if (foro != null) {
			texto.setText(((Foro) foro).getTitulo());
		}
		return vistaFila;
	}

}
