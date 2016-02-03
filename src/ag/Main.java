package ag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static AlgoritmoGenetico ag;
    public static void main(String[] args) throws IOException {
        lerArquivo("Teste_01.dat");
        ag.executar();
    }
    
    public static void lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException{
        FileInputStream stream = new FileInputStream(nomeArquivo);
        InputStreamReader reader = new InputStreamReader(stream);
        //InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        
        //num linhas
        String strNumLinha = linha.substring(linha.indexOf(" ")).trim();
        int numLinha = Integer.parseInt(strNumLinha);
        
        linha = br.readLine();
        
        //num coluna
        String strNumColuna = linha.substring(linha.indexOf(" ")).trim();
        int numColuna = Integer.parseInt(strNumColuna);
        
        ag = new AlgoritmoGenetico(numLinha, numColuna);
        
        br.readLine();
        
        //System.out.println("LINHA: " + numLinha);
        //System.out.println("COLUNA: " + numColuna);
        
        for (int i = 0; i < numColuna; i++) {
            linha = br.readLine();
            String dados[] = linha.split("\\s+");
            
            double custo = Double.parseDouble(dados[2]);
            ag.addCusto(i, custo);
           
            for (int j = 3; j < dados.length; j++) {
                ag.addDados(i, Integer.parseInt(dados[j]));
                //System.out.print(dados[j] + " - ");
            }
            //System.out.println();
        }
        
    }
}
