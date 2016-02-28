package ag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static AlgoritmoGenetico ag;
    
    /*
    *   PARÂMETROS DO ALGORITMO GENÉTICO
    */
    
    // TAMANHO FIXO DA POPULAÇÃO
    private static final int TAM_POPULACAO = 1000;
    
    // TAXA MÍNIMA DE MUTAÇÃO
    private static final double TAXA_MIN_MUTACAO = 0.1;
    
    // CONDIÇÃO DE PARADA: NÚMERO MÁXIMO DE GERAÇÕES SEM ATUALIZAR
    private static final int NUM_GERACOES = 1000;
    
    private static final String NOME_ARQUIVO = "Teste_01.dat";
    
    public static void main(String[] args) throws IOException {
        lerArquivo(NOME_ARQUIVO);
        ag.executar();
    }
    
    public static void lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException{
        FileInputStream stream = new FileInputStream(nomeArquivo);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        
        //num linhas
        String strNumLinha = linha.substring(linha.indexOf(" ")).trim();
        int numLinha = Integer.parseInt(strNumLinha);
        
        linha = br.readLine();
        
        //num coluna
        String strNumColuna = linha.substring(linha.indexOf(" ")).trim();
        int numColuna = Integer.parseInt(strNumColuna);
        
        ag = new AlgoritmoGenetico(numLinha, numColuna, TAM_POPULACAO, TAXA_MIN_MUTACAO, NUM_GERACOES);
        
        br.readLine();
        
        for (int i = 0; i < numColuna; i++) {
            linha = br.readLine();
            String dados[] = linha.split("\\s+");
            
            double custo = Double.parseDouble(dados[2]);
            ag.addCusto(i, custo);
           
            for (int j = 3; j < dados.length; j++) {
                ag.addDados(i, Integer.parseInt(dados[j]));
            }
        }
        
    }
}
