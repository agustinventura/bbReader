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
import es.aguasnegras.bbReader.entidades.PaginaForos;
import es.aguasnegras.bbReader.utils.PaginaForosAdapter;

public class MostrarPaginaForos extends Activity {

	private PaginaForos paginaForos = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagina_foros);
		String urlForo = this.getIntent().getExtras().getString("urlForo");
		this.cargarForos(urlForo);
		this.mostrarForos();
	}

	private void mostrarForos() {
		ListView vistaLista = (ListView) this
				.findViewById(R.id.listaPaginaForos);
		PaginaForosAdapter adaptador = new PaginaForosAdapter(this,
				this.paginaForos);
		vistaLista.setAdapter(adaptador);
	}

	private void cargarForos(String urlForo) {
		this.paginaForos = new PaginaForos();
		this.paginaForos.setUrl(urlForo);
		Document pagina = null;
		try {
			pagina = Jsoup.connect(paginaForos.getUrl()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pagina != null) {
			List<Element> linksForos = this.cargarForos(pagina);
			this.paginaForos.cargarForos(linksForos);
		}
	}

	private List<Element> cargarForos(Document pagina) {
		List<Element> forosRaices = new ArrayList<Element>();
		this.buscarLinkRaiz(pagina.getElementsByTag("body").get(0), forosRaices);
		return forosRaices;
	}

	private void buscarLinkRaiz(Element raiz, List<Element> forosRaices) {
		if (raiz.attr("href").contains("viewforum.php")) {
			forosRaices.add(raiz);
		} else {
			Elements hijos = raiz.children();
			for (int i = 0; i < hijos.size(); i++) {
				Element raizPosible = hijos.get(i);
				this.buscarLinkRaiz(raizPosible, forosRaices);
			}
		}
	}
}
