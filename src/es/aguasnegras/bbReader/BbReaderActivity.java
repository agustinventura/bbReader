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
		String urlForo = editUrl.getText().toString();
		if (urlForo.isEmpty()
				|| urlForo.equalsIgnoreCase(this.getString(R.string.urlForo))) {
			Toast.makeText(this, "Dirección no válida", Toast.LENGTH_LONG);
		} else {
			this.cargarPaginaForos(urlForo);
		}
	}

	private void cargarPaginaForos(String urlForo) {
		Intent intent = new Intent(this, MostrarPaginaForos.class);
		intent.putExtra("urlForo", urlForo);
		startActivity(intent);
	}
}