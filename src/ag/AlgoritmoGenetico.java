package ag;

import java.util.ArrayList;

public class AlgoritmoGenetico {
    
    public static final int TAM_POPULACAO = 1000;
    public static final double TAXA_MIN_MUTACAO = 0.5;
    public static final int NUM_GERACOES = 1000;
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
        int i = 0;
        while (i < NUM_GERACOES){
            Cromossomo filho = crossover();
            
            Cromossomo maisApto = populacao.maisApto();
            Cromossomo menosApto = populacao.menosApto();
            
            double random_double = Util.getRandomDouble();
            if (random_double < taxaMutacao(maisApto.getCustoTotal(), menosApto.getCustoTotal())){
                mutacao(filho);
            }
            
            if (filho.getCustoTotal() < menosApto.getCustoTotal()){
                populacao.atualizar(filho);
                i = 0;
            }
            else {
                i++;
            }
            System.out.print(i);
            System.out.println(" -> "+populacao.maisApto().getCustoTotal() + " - Media: "+populacao.media);
        }
        /*for (Cromossomo cromossomo : populacao.getPopulacao()) {
            System.out.println(cromossomo.getColunas());
        }*/
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
    
    public Cromossomo crossover(){
        Cromossomo pai_x = selecao();
        Cromossomo pai_y = selecao();
        
        while (pai_x == pai_y){
            pai_y = selecao();
        }
        
        ArrayList<Integer> uniao = Util.uniao(pai_x.getColunas(), pai_y.getColunas());
        Cromossomo filho = new Cromossomo(uniao, listaColuna, listaLinha, listaCusto);
        filho.eliminaRedundancia(listaColuna, listaCusto);
        return filho;
    }
    
    public void mutacao(Cromossomo C){
        double random_double = Util.getRandomDouble();
        int n = (int)(random_double * C.getColunas().size());
        for (int i = 0; i < n; i++) {
            int random_col = Util.getRandomInt(listaColuna.length);
            C.addColuna(random_col, listaCusto[random_col], listaColuna);
        }
        C.eliminaRedundancia(listaColuna, listaCusto);
    }
    
    public Double taxaMutacao(Double custoMaisApto, Double custoMenosApto){
        double taxa = TAXA_MIN_MUTACAO;
        taxa = taxa / (1 - Math.exp((-custoMenosApto - custoMaisApto) / custoMenosApto));
        return taxa;
    }
}
