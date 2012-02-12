package es.aguasnegras.bbReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import es.aguasnegras.bbReader.entidades.MetaForo;
import es.aguasnegras.bbReader.utils.MetaForoAdapter;

public class MostrarMetaForo extends Activity {

	private MetaForo metaForo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metaforo);
		String urlForo = this.getIntent().getExtras().getString("urlForo");
		this.cargarForos(urlForo);
		this.mostrarForos();
	}

	private void mostrarForos() {
		ListView vistaLista = (ListView) this
				.findViewById(R.id.listaPaginaForos);
		MetaForoAdapter adaptador = new MetaForoAdapter(this, this.metaForo);
		vistaLista.setAdapter(adaptador);
	}

	private void cargarForos(String urlForo) {
		this.metaForo = new MetaForo();
		this.metaForo.setUrl(urlForo);
		Document pagina = null;
		try {
			pagina = Jsoup.connect(metaForo.getUrl()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pagina != null) {
			List<Element> foros = this.cargarMetaForos(pagina);
			this.metaForo.cargarForos(foros);
		}
	}

	private List<Element> cargarMetaForos(Document pagina) {
		Elements links = pagina.select("a[href]");
		List<Element> metaforos = new ArrayList<Element>();
		for (int i = 0; i < links.size(); i++) {
			Element candidato = links.get(i);
			if (candidato.attr("href").contains("viewforum")) {
				boolean esMetaForo = this.comprobarMetaForo(links, i);
				if (esMetaForo) {
					metaforos.add(candidato);
				}
			}
		}
		return metaforos;
	}

	private boolean comprobarMetaForo(Elements links, int i) {
		boolean esMetaForo = false;
		int posicionSiguienteLink = i;
		posicionSiguienteLink++;
		int posicionAnteriorLink = i;
		posicionAnteriorLink--;
		// TODO: Esta condición de posición de los índices no esta muy fina
		if (posicionSiguienteLink < links.size() && posicionAnteriorLink > 0) {
			// El siguiente link al del metaforo ha de ser un foro
			Element siguienteLink = links.get(posicionSiguienteLink);
			// Pero su padre no puede ser un foro
			Element anteriorLink = links.get(posicionAnteriorLink);
			if (siguienteLink.attr("href").contains("viewforum")
					&& !anteriorLink.attr("href").contains("viewforum")) {
				esMetaForo = true;
			}
		}
		return esMetaForo;
	}
}
