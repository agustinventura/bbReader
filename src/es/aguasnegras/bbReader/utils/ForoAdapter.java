package es.aguasnegras.bbReader.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.aguasnegras.bbReader.R;
import es.aguasnegras.bbReader.entidades.Foro;

public class ForoAdapter extends BaseAdapter {

	private Foro foro = null;
	private Context contexto = null;

	public ForoAdapter(Context contexto, Foro foro) {
		this.contexto = contexto;
		this.foro = foro;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
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
		if (this.foro != null) {
			if (this.foro.getForos() != null) {
				numeroElementos = this.foro.getForos().size();
			}
		}
		return numeroElementos;
	}

	@Override
	public Object getItem(int posicion) {
		Object resultado = null;
		if (this.foro != null && this.foro.getForos() != null && posicion >= 0
				&& posicion < this.foro.getForos().size()) {
			resultado = this.foro.getForos().get(posicion);
		}
		return resultado;
	}

	@Override
	public long getItemId(int posicion) {
		return posicion;
	}

	@Override
	public View getView(int posicion, View convertView, ViewGroup parentView) {
		View vistaFila = convertView;
		LayoutInflater inflater = (LayoutInflater) contexto
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		vistaFila = inflater.inflate(R.layout.fila_foro, parentView, false);
		TextView texto = (TextView) vistaFila.findViewById(R.id.filaForo);

		Object foro = this.getItem(posicion);
		if (foro != null) {
			texto.setText(((Foro) foro).getPagina().getTitulo());
		}
		return vistaFila;
	}
}
