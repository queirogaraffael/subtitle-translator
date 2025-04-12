package service;

import GoogleAPI.GoogleTranslateConnection;

public class TranslatorFactory {
	public static TranslatorInterface createTranslator() throws Exception {

		return new GoogleTranslator(GoogleTranslateConnection.GoogleTranslateService());

	}
}
