package service;

import java.util.List;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;

import model.entities.Word;
import views.MensagemUtils;

public class GoogleTranslator implements TranslatorInterface {

	private Translate translate;

	public GoogleTranslator(Translate translate) {
		this.translate = translate;
	}

	@Override
	public String tradutorPalavra(String text, String idiomaASerTraduzido) {

		Translation translation = translate.translate(text,
				Translate.TranslateOption.targetLanguage(idiomaASerTraduzido));
		return translation.getTranslatedText();
	}

	@Override
	public void traduzListaPalavras(List<Word> words, String idiomaTraduzir) throws Exception {
		words.forEach(p -> {
			try {
				p.setWordTranslated(tradutorPalavra(p.getWord(), idiomaTraduzir));
			} catch (Exception e) {
				MensagemUtils.mostrarErro(e.toString());
			}
		});

	}

}
