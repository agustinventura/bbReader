package es.aguasnegras.bbReader.entidades;

import java.util.ArrayList;
import java.util.List;

public class Foro {

	private Pagina pagina;

	private List<Foro> foros;

	private List<Tema> temas;

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public Foro(Pagina pagina) {
		this.foros = new ArrayList<Foro>();
		this.temas = new ArrayList<Tema>();
		this.pagina = pagina;
	}

	public List<Foro> getForos() {
		return foros;
	}

	public void setForos(List<Foro> foros) {
		this.foros = foros;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pagina == null) ? 0 : pagina.hashCode());
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
		if (pagina == null) {
			if (other.pagina != null)
				return false;
		} else if (!pagina.equals(other.pagina))
			return false;
		return true;
	}
}
