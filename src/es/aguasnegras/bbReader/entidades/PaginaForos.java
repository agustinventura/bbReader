package es.aguasnegras.bbReader.entidades;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.text.Html;

public class PaginaForos {

	private String url;

	private String nombre;

	private List<Foro> foros;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Foro> getForos() {
		return foros;
	}

	public void setForos(List<Foro> foros) {
		this.foros = foros;
	}

	public void cargarForos(Document paginaForos) {
		if (paginaForos != null) {
			Elements forosHtml = paginaForos.select("a[href]");
			if (!forosHtml.isEmpty()) {
				this.foros = new ArrayList<Foro>();
				for (Element foroHtml : forosHtml) {
					if (foroHtml.attr("href").contains("viewforum.php")) {
						Foro foro = new Foro();
						foro.setTitulo(Html.fromHtml(foroHtml.text())
								.toString());
						foro.setUrl(foroHtml.attr("abs:href"));
						this.foros.add(foro);
					}
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaginaForos other = (PaginaForos) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaginaForos [url=" + url + "]";
	}
}
