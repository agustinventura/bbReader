package es.aguasnegras.bbReader.entidades;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import android.text.Html;

public class Foro {

	private String url;

	private String titulo;

	private List<Foro> foros;

	public Foro() {
		this.foros = new ArrayList<Foro>();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Foro> getForos() {
		return foros;
	}

	public void setForos(List<Foro> foros) {
		this.foros = foros;
	}

	public void cargarForos(List<Element> forosHtml) {
		this.foros = new ArrayList<Foro>();
		for (Element foroHtml : forosHtml) {
			Foro foro = new Foro();
			foro.setTitulo(Html.fromHtml(foroHtml.text()).toString());
			foro.setUrl(foroHtml.attr("abs:href"));
			this.foros.add(foro);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Foro other = (Foro) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foro [url=" + url + ", titulo=" + titulo + "]";
	}
}
