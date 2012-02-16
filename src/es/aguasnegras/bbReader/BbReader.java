package es.aguasnegras.bbReader;

import android.app.Application;
import es.aguasnegras.bbReader.entidades.Foro;

public class BbReader extends Application {

	private Foro foro;

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}
}
