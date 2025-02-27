# Tradutor de Legendas
Este é um tradutor de legendas que permite a tradução de palavras em arquivos de legendas de vídeo (.srt). O projeto utiliza a API do Google Translate para realizar as traduções de um idioma qualquer (que tenha suporte no Google Tradutor) e permite traduzir para o inglês, português ou alemão.

## Funcionalidades
1. Leitura de arquivos de legendas no formato .srt.
2. Filtragem de palavras válidas paras tradução.
3. Análise da frequência de palavras nas legendas.
4. Tradução de palavras utilizando a API do Google Translate.
5. Geração de um novo arquivo de legendas com as palavras traduzidas, listadas em ordem de frequência das palavras mais frequentes.

## Pré-requisitos
Conta no Google Cloud: Para utilizar este projeto, você precisa ter uma conta no Google Cloud e uma chave de API válida para a API do Google Translate.

## Configuração da Chave da API do Google Translate

Para configurar a chave da API, siga os passos:
1. Crie sua chave de API no Google Cloud, ativando a API do Google Translate.
2. Adicione a chave ao atributo apiKey da classe GoogleTranslateConnection.

## Como Usar
1. Clone este repositório para o seu ambiente local.
2. Configure sua chave de API do Google Translate na classe GoogleTranslateConnection.
3. Execute o programa principal.
4. Selecione o arquivo de legenda que deseja traduzir. O programa identificará automaticamente o idioma do arquivo.
5. Escolha o idioma para o qual deseja traduzir (inglês, português ou alemão).
6. Aguarde enquanto o programa processa e traduz as legendas.
7. O novo arquivo de legenda traduzido será gerado na mesma pasta do arquivo original.

## Exemplo de arquivo .srt traduzido do inglês para o português.

```
822
01:32:09,300 --> 01:32:11,384
Right.
So...

823
01:32:11,385 --> 01:32:13,511
- See you later.
- See you later.
```

### Resultado:

```
Frequência - Tradução 

2          -  later 
2          -  See 
2          -  you
1          -  right
1          -  so
```

## Ferramentas Utilizadas
1. Java: Linguagem principal utilizada no desenvolvimento.
2. Google Translate API: Para realizar as traduções automáticas.
3. Biblioteca para Leitura de Arquivos SRT: Ferramenta para manipulação dos arquivos de legenda .srt.
4. Maven: Gerenciador de dependências e build automation.


## Contribuição
Contribuições são bem-vindas! Se você deseja contribuir com melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença
Este projeto é licenciado sob a [Licença MIT](https://github.com/queirogaraffael/subtitle-translate/blob/main/LICENSE).
