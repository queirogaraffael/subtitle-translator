package controller;

import GoogleAPI.GoogleTranslateConnection;
import commons.constantes.ConstantesOpcoes;
import commons.util.FileManager;
import model.entities.Word;
import service.TranslatorFactory;
import service.TranslatorInterface;
import views.FalhaArquivoView;
import views.IdiomasView;
import views.TraducaoFinalizadaView;

import javax.swing.*;
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

            String caminhoArquivoLegendaString = JOptionPane
                    .showInputDialog("Digite o caminho do arquivo da legenda: ");

            while (true) {

                if (!FileManager.arquivoExiste(caminhoArquivoLegendaString)
                        || !FileManager.diretorioEhValido(caminhoArquivoLegendaString)) {
                    int opcao = FalhaArquivoView.view();

                    if (opcao == ConstantesOpcoes.SIM) {
                        caminhoArquivoLegendaString = JOptionPane
                                .showInputDialog("Digite o caminho do arquivo da legenda: ");

                    } else {
                        GoogleTranslateConnection.clearTranslateService();
                        System.exit(0);
                    }

                } else {
                    break;
                }

            }

            String idiomaTraduzir = IdiomasView.idiomaParaTraduzir();

            JOptionPane.showMessageDialog(null,
                    "Aguarde o processamento ser finalizado. O tempo depende do tamanho do arquivo!");


            FileManager.processarPalavrasDoArquivoDeLegenda(words, caminhoArquivoLegendaString);
            translator.traduzListaPalavras(words, idiomaTraduzir);
            Collections.sort(words);


            String nomeArquivoFormatado = FileManager.formatarNomeArquivo(caminhoArquivoLegendaString);
            String caminhoParaSalvarArquivoTraduzido = FileManager
                    .obterCaminhoDiretorio(caminhoArquivoLegendaString);


            FileManager.salvarArquivoComFrequencia(caminhoParaSalvarArquivoTraduzido, nomeArquivoFormatado, words);

            TraducaoFinalizadaView.view(caminhoParaSalvarArquivoTraduzido, nomeArquivoFormatado);

        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        } finally {
            GoogleTranslateConnection.clearTranslateService();
            System.exit(0);
        }

    }

}
