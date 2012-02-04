package es.aguasnegras.bbReader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;

public class MostrarPagina {

	private Document pagina = null;

	private String textoPagina = null;

	public void onCreate(Bundle savedInstanceState) {
		// super.onCreate(savedInstanceState);

		this.cargarPagina();

		this.parsearPagina();

		this.mostrarPagina();
	}

	private void mostrarPagina() {
		// setContentView(R.layout.main);
		// TextView texto = (TextView) findViewById(R.id.texto);
		// texto.setText(textoPagina);
	}

	private void cargarPagina() {
		try {
			this.pagina = Jsoup.connect("http://www.clubcbf.es/foro").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parsearPagina() {
		Elements foros = pagina
				.select("div.forabg > div.inner > ul.topiclist > li.header > dl.icon > dt > a");
		StringBuilder resultado = new StringBuilder("");
		for (Element foro : foros) {
			resultado.append(foro.html());
		}
		this.textoPagina = resultado.toString();
	}
}
