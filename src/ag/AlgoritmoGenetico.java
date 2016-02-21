package ag;

import java.util.ArrayList;

public class AlgoritmoGenetico {
    
    public static final int TAM_POPULACAO = 500;
    public static final int QTD_TORNEIO = 5;
    
    //pode ser vetor e pode ser hash set 
    private ArrayList<Integer>[] listaColuna;
    private ArrayList<Integer>[] listaLinha;
    private Double[] listaCusto;
    
    private Populacao populacao;
    
    public AlgoritmoGenetico(int nLinha, int nColuna){
        listaColuna = new ArrayList[nColuna];
        listaLinha = new ArrayList[nLinha];
        listaCusto = new Double[nColuna];
        populacao = new Populacao(TAM_POPULACAO);
    }
    
    public void addCusto(int coluna, double custo){
        listaCusto[coluna] = custo;
    }
    
    public void addDados(int coluna, int linha){
        linha--;
        if (listaColuna[coluna] == null){
            listaColuna[coluna] = new ArrayList<>();
        }
        
        if (listaLinha[linha] == null){
            listaLinha[linha] = new ArrayList<>();
        }
        
        listaColuna[coluna].add(linha);
        listaLinha[linha].add(coluna);
    }
    
    public void imprimir(){
        for (int i = 0; i < listaColuna.length; i++) {
            System.out.print(listaCusto[i]+" - ");
            System.out.println(listaColuna[i]);
        }
        for (int i = 0; i < listaLinha.length; i++) {
            System.out.println(listaLinha[i]);
        }
    }
    
    public void executar(){
        populacao.gerarPopulacaoInicial(listaLinha, listaColuna, listaCusto);
        selecao();
    }
    
    // Seleção de individuos por torneio
    public Cromossomo selecao(){
        Cromossomo c = null;
        for (int i = 0; i < QTD_TORNEIO; i++) {
            int random_pos = Util.getRandomInt(TAM_POPULACAO);
            Cromossomo rand = populacao.getPopulacao()[random_pos];
            if (c == null || rand.getCustoTotal() < c.getCustoTotal()){
                c = rand;
            }
        }
        return c;
    }
}
