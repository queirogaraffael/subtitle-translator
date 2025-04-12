package commons.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import commons.constantes.ConstantesGeral;
import model.entities.Word;

public class FileManager {

	public static boolean diretorioEhValido(String caminhoLegenda) {
		File arquivoLegenda = new File(caminhoLegenda);
		File diretorio = arquivoLegenda.getParentFile();

		if (diretorio == null || !diretorio.isDirectory()) {
			return false;
		}

		File arquivoTeste = new File(diretorio, "teste.srt");
		try {
			return arquivoTeste.createNewFile() && arquivoTeste.delete();
		} catch (IOException e) {
			return false;
		}
	}


	public static boolean arquivoExiste(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);
		return arquivo.exists();
	}


	public static void salvarArquivoComFrequencia(String caminhoDiretorio, String nomeArquivo, List<Word> palavras) throws IOException {
		File diretorio = new File(caminhoDiretorio);
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		String nomeCompletoArquivo = ConstantesGeral.nomeArquivoFinal + nomeArquivo;
		File arquivo = new File(diretorio, nomeCompletoArquivo);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
			for (Word palavra : palavras) {
				writer.write(palavra.toString());
				writer.newLine();
			}
		}
	}


	public static void processarPalavrasDoArquivoDeLegenda(List<Word> words, String caminhoArquivoLegenda) throws Exception {
		try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoLegenda))) {
			String linha;
			while ((linha = leitor.readLine()) != null) {
				if (linha.trim().isEmpty() || linha.contains(" --> ")) {
					continue;
				}

				processarLinha(linha, words);
			}
		}
	}

	private static void processarLinha(String linha, List<Word> words) {
		String[] palavras = linha.split(" ");
		for (String palavra : palavras) {
			String palavraFiltrada = FiltroDeCaracteresEspeciais.filter(palavra.toLowerCase());
			if (palavraFiltrada.isEmpty()) {
				continue;
			}

			Word novaPalavra = new Word(palavraFiltrada);
			int indice = words.indexOf(novaPalavra);
			if (indice >= 0) {
				words.get(indice).adicionaFrequencia();
			} else {
				words.add(novaPalavra);
			}
		}
	}


	public static String formatarNomeArquivo(String caminhoArquivoLegenda) {
		File arquivoLegenda = new File(caminhoArquivoLegenda);
		String nomeArquivo = arquivoLegenda.getName();
		return Character.toUpperCase(nomeArquivo.charAt(0)) + nomeArquivo.substring(1);
	}

	public static String obterCaminhoDiretorio(String caminhoArquivoLegenda) {
		File arquivoLegenda = new File(caminhoArquivoLegenda);
		return arquivoLegenda.getParent();
	}

}
