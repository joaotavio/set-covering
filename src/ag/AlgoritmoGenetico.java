package ag;

import java.util.ArrayList;

public class AlgoritmoGenetico {
    
    public int TAM_POPULACAO = 500;
    public double TAXA_MIN_MUTACAO = 0.1;
    public int NUM_GERACOES = 1000;
    public static final int QTD_TORNEIO = 2;
    
    //pode ser vetor e pode ser hash set 
    private final ArrayList<Integer>[] listaColuna;
    private final ArrayList<Integer>[] listaLinha;
    private final Double[] listaCusto;
    
    private final Populacao populacao;
    
    public AlgoritmoGenetico(int nLinha, int nColuna, int tam_populacao, double taxa_min_mutacao, int num_geracoes){
        TAM_POPULACAO = tam_populacao;
        TAXA_MIN_MUTACAO = taxa_min_mutacao;
        NUM_GERACOES = num_geracoes;
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
        }
        System.out.println("MELHOR SOLUÇÂO:");
        System.out.println("COLUNAS: "+populacao.maisApto().getColunas());
        System.out.println("CUSTO: "+populacao.maisApto().getCustoTotal());
        
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
