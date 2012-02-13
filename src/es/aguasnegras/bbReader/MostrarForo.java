package es.aguasnegras.bbReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import es.aguasnegras.bbReader.utils.ForoAdapter;

public class MostrarForo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foro);
		this.mostrarForos();
	}

	private void mostrarForos() {
		ListView vistaLista = (ListView) this.findViewById(R.id.listaForos);
		BbReader bbReader = (BbReader) this.getApplicationContext();
		ForoAdapter adaptador = new ForoAdapter(this, bbReader.getForo());
		vistaLista.setAdapter(adaptador);
	}
}
