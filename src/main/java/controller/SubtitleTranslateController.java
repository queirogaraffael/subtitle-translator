package controller;

import GoogleAPI.GoogleTranslateConnection;
import commons.constantes.ConstantesOpcoes;
import commons.util.FileManager;
import model.entities.Word;
import service.TranslatorFactory;
import service.TranslatorInterface;
import views.FalhaArquivoView;
import views.IdiomasView;
import views.MensagemUtils;
import views.TraducaoFinalizadaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubtitleTranslateController {

    private TranslatorInterface translator;

    public SubtitleTranslateController() throws Exception {
        this.translator = TranslatorFactory.createTranslator();
    }

    public void MainProgram() throws Exception {

        try {

            List<Word> words = new ArrayList<Word>();

            String caminhoArquivoLegendaString = MensagemUtils.solicitarCaminhoLegenda();

            while (true) {

                if (!FileManager.arquivoExiste(caminhoArquivoLegendaString)
                        || !FileManager.diretorioEhValido(caminhoArquivoLegendaString)) {
                    int opcao = FalhaArquivoView.view();

                    if (opcao == ConstantesOpcoes.SIM) {
                        caminhoArquivoLegendaString = MensagemUtils.solicitarCaminhoLegenda();

                    } else {
                        GoogleTranslateConnection.clearTranslateService();
                        System.exit(0);
                    }

                } else {
                    break;
                }

            }

            String idiomaTraduzir = IdiomasView.idiomaParaTraduzir();

            MensagemUtils.mostrarMensagemProcessamento();

            FileManager.processarPalavrasDoArquivoDeLegenda(words, caminhoArquivoLegendaString);
            translator.traduzListaPalavras(words, idiomaTraduzir);
            Collections.sort(words);


            String nomeArquivoFormatado = FileManager.formatarNomeArquivo(caminhoArquivoLegendaString);
            String caminhoParaSalvarArquivoTraduzido = FileManager
                    .obterCaminhoDiretorio(caminhoArquivoLegendaString);


            FileManager.salvarArquivoComFrequencia(caminhoParaSalvarArquivoTraduzido, nomeArquivoFormatado, words);

            TraducaoFinalizadaView.view(caminhoParaSalvarArquivoTraduzido, nomeArquivoFormatado);

        } catch (IOException erro) {
            MensagemUtils.mostrarErro(erro.toString());
        } finally {
            GoogleTranslateConnection.clearTranslateService();
            System.exit(0);
        }

    }

}
