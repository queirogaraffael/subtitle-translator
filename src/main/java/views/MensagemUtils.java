package views;

import javax.swing.JOptionPane;

public class MensagemUtils {

    public static String solicitarCaminhoLegenda() {
        return JOptionPane.showInputDialog("Digite o caminho do arquivo da legenda:");
    }

    public static void mostrarMensagemProcessamento() {
        JOptionPane.showMessageDialog(null,
                "Aguarde o processamento ser finalizado. O tempo depende do tamanho do arquivo!");
    }

    public static void mostrarErro(String erro) {
        JOptionPane.showMessageDialog(null, "Erro: " + erro);
    }
}
