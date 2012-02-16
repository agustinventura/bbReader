package es.aguasnegras.bbReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.servicios.ServicioForos;
import es.aguasnegras.bbReader.utils.ForoAdapter;

public class MostrarForo extends Activity implements OnItemClickListener {

	private Foro foro;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foro);
		this.cargarForo();
		this.mostrarForos();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Foro foroSeleccionado = this.foro.getForos().get(position);
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(foroSeleccionado.getPagina().getTitulo() + ": "
				+ foroSeleccionado.getPagina().getUrl());
		alertbox.show();
	}

	private void cargarForo() {
		String urlForo = (String) this.getIntent().getExtras().get("urlForo");
		ServicioForos servicioForos = new ServicioForos();
		this.foro = servicioForos.cargarForo(urlForo);
	}

	private void mostrarForos() {
		ListView vistaLista = (ListView) this.findViewById(R.id.listaForos);
		ForoAdapter adaptador = new ForoAdapter(this, this.foro);
		vistaLista.setAdapter(adaptador);
		vistaLista.setOnItemClickListener(this);
	}
}
