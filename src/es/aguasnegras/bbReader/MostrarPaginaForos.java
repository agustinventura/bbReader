package es.aguasnegras.bbReader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
		vistaLista.setAdapter(adaptador.getAdaptador());
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
		this.paginaForos.cargarForos(pagina);
	}
}
