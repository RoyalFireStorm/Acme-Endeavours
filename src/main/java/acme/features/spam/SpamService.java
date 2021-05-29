package acme.features.spam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;

@Service
public class SpamService {

	@Autowired
	private SpamRepository spamRep;

	public boolean filtroSpam(final String text, final Double umbralMaximo) { 
		
		final List<Spam> todoSpam= this.spamRep.findAllSpam();
		final List<String>spamWords= new ArrayList<String>();
		for(int j=0; j<todoSpam.size();j++) {
			spamWords.add(todoSpam.get(j).getSpamWords());
		}	
		
		final String textoConFormato = this.formatearTexto(text);
		final String[] textoCortado= textoConFormato.split("\\s+");
		Integer procentajeSpamDetectado = 0;
		final Integer tamanoTexto=textoCortado.length;

		for(int i=0; i<spamWords.size(); i++) {
			final String palabra = this.formatearTexto(spamWords.get(i));
			if(textoConFormato.contains(palabra)){
				procentajeSpamDetectado=procentajeSpamDetectado+(100/tamanoTexto);
			}
		}
		if(umbralMaximo<procentajeSpamDetectado) {
			return true;
		}else {
			return false ;	
		}
		
	}
	
	private String formatearTexto(final String texto) {
		return texto.toLowerCase().replace(".", " ").replace(":", " ").replace(";", " ")
		.replace(",", " ").replace("(", " ").replace(")", " ").replace("-", " ").replace("_", " ")
		.replace(">", " ").replace("<", " ").replace("¡", " ").replace("?", " ").replace("¿", " ")
		.replace("!", " ").replace("}", " ").replace("{", " ").replace("*", " ").replace("+", " ").trim();
	}

}
