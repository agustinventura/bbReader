package es.aguasnegras.bbReader.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.aguasnegras.bbReader.R;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.entidades.MetaForo;

public class MetaForoAdapter extends BaseAdapter {

	private MetaForo metaForo = null;
	private Context contexto = null;

	public MetaForoAdapter(Context contexto, MetaForo metaForo) {
		this.contexto = contexto;
		this.setMetaForo(metaForo);
	}

	public MetaForo getMetaForo() {
		return metaForo;
	}

	public void setMetaForo(MetaForo metaForo) {
		this.metaForo = metaForo;
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
		if (this.metaForo != null) {
			if (this.metaForo.getForos() != null) {
				numeroElementos = this.metaForo.getForos().size();
			}
		}
		return numeroElementos;
	}

	@Override
	public Object getItem(int posicion) {
		Object resultado = null;
		if (this.metaForo != null && this.metaForo.getForos() != null
				&& posicion >= 0 && posicion < this.metaForo.getForos().size()) {
			resultado = this.metaForo.getForos().get(posicion);
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
