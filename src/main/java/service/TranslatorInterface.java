package service;

import java.util.List;

import model.entities.Word;

public interface TranslatorInterface {

	String tradutorPalavra(String palavra, String idiomaASerTraduzido) throws Exception;

	void traduzListaPalavras(List<Word> words, String idiomaTraduzir) throws Exception;
}
