package es.aguasnegras.bbReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.servicios.ServicioForos;
import es.aguasnegras.bbReader.utils.ForoAdapter;

public class MostrarForo extends Activity {

	private Foro foro;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foro);
		this.cargarForo();
		this.mostrarForos();
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
	}
}
