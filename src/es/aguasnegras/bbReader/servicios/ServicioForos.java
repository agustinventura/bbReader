package es.aguasnegras.bbReader.servicios;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.text.Html;
import es.aguasnegras.bbReader.entidades.Foro;
import es.aguasnegras.bbReader.entidades.Pagina;

public class ServicioForos {

	public Foro cargarForo(String urlForo) {
		Pagina raizForo = new Pagina(urlForo, urlForo);
		Foro foro = new Foro(raizForo);
		Document pagina = this.cargarPagina(foro);
		if (pagina != null) {
			this.parsearForo(pagina, foro);
		}
		return foro;
	}

	private Document cargarPagina(Foro foro) {
		Document pagina = null;
		try {
			pagina = Jsoup.connect(foro.getPagina().getUrl()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pagina;
	}

	private void parsearForo(Document pagina, Foro foro) {
		// Primero se determina el tipo de página: bien es de categorías o foros
		// raices, bien es de foro normal o tema
		boolean esPaginaRaiz = this.esPaginaRaiz(pagina);
		if (esPaginaRaiz) {
			this.cargarPaginaRaiz(pagina, foro);
		} else {
			this.cargarForosYTemas(pagina, foro);
		}
	}

	private boolean esPaginaRaiz(Document pagina) {
		boolean esPaginaRaiz = false;
		Elements categorias = pagina.select("a[href*=index.php?c=]");
		if (categorias.isEmpty()) {
			categorias = pagina.select("a[href*=viewforum.php?f=]");
			int i = 0;
			while (i < categorias.size() && !esPaginaRaiz) {
				Element candidato = categorias.get(i);
				esPaginaRaiz = this.esForoRaiz(candidato);
				i++;
			}
		} else {
			esPaginaRaiz = true;
		}

		return esPaginaRaiz;
	}

	private void cargarPaginaRaiz(Document pagina, Foro foro) {
		Elements categorias = pagina.select("a[href*=index.php?c=]");
		if (categorias.isEmpty()) {
			categorias = pagina.select("a[href*=viewforum.php?f=]");
		}
		for (int i = 0; i < categorias.size(); i++) {
			Element candidato = categorias.get(i);
			if (this.esForoRaiz(candidato)) {
				foro.getForos().add(this.convertirForo(candidato));
			}
		}

	}

	private void cargarForosYTemas(Document pagina, Foro foro) {
		// TODO Auto-generated method stub

	}

	private boolean esForoRaiz(Element candidato) {
		boolean esForoRaiz = false;
		if (!candidato.attr("href").contains("index.php?c=")) {
			Element padre = candidato.parent();
			if (padre.children().size() == 1) {
				esForoRaiz = true;
			}
		} else {
			esForoRaiz = true;
		}
		return esForoRaiz;
	}

	private Foro convertirForo(Element elementForo) {
		Pagina enlaceForo = new Pagina(elementForo.attr("abs:href"), Html
				.fromHtml(elementForo.text()).toString());
		Foro foro = new Foro(enlaceForo);
		return foro;
	}
}
