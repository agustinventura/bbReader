package es.aguasnegras.bbReader;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BbReaderActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HttpClient cliente = new DefaultHttpClient();
		HttpGet peticion = new HttpGet("http://www.clubcbf.es/foro");

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response_str = "";

		try {
			response_str = cliente.execute(peticion, responseHandler);
		} catch (ClientProtocolException e) {
			response_str = e.getLocalizedMessage();
		} catch (IOException e) {
			response_str = e.getLocalizedMessage();
		}

		setContentView(R.layout.main);
		TextView texto = (TextView) findViewById(R.id.texto);
		texto.setText(response_str);
	}
}