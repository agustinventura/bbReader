package es.aguasnegras.bbReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BbReaderActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void botonEntrarListener(View v) {
		EditText editUrl = (EditText) findViewById(R.id.urlForo);
		String urlForo = this.comprobarHttp(editUrl.getText().toString());
		if (!urlForo.isEmpty()) {
			// TODO: Cuidado, esto ya no es tan fácil, hay que comprobar si hay
			// que mostrar un metaforo o un foro
			this.cargarPaginaForos(urlForo);
		}
	}

	private String comprobarHttp(String urlForo) {
		if (urlForo.isEmpty()
				|| urlForo.equalsIgnoreCase(this.getString(R.string.urlForo))) {
			Toast.makeText(this, "Dirección no válida", Toast.LENGTH_LONG);
		} else {
			String http = urlForo.substring(0, 6);
			if (!http.equalsIgnoreCase("http://")) {
				urlForo = "http://" + urlForo;
			}
		}
		return urlForo;
	}

	private void cargarPaginaForos(String urlForo) {
		Intent intent = new Intent(this, MostrarMetaForo.class);
		intent.putExtra("urlForo", urlForo);
		startActivity(intent);
	}
}