package ag;

import java.util.ArrayList;
import java.util.Arrays;

public class Cromossomo {
    
    //Lista de indices das colunas que pertencem a solução
    private ArrayList<Integer> colunas;
    private double custoTotal;

    public Cromossomo() {
        colunas = new ArrayList<>();
        custoTotal = 0;
    }
    
    public void addColuna(int index, double custo){
        colunas.add(index);
        custoTotal += custo;
    }

    public ArrayList<Integer> getColunas() {
        return colunas;
    }
    
    public static void gerarIndividuo(ArrayList<Integer>[] listaLinha, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        //listaLinha = ALFA
        //listaColuna = BETA
        
        ArrayList<Integer> linhasDescobertas = new ArrayList<>();
        for (int i = 0; i < listaLinha.length; i++) {
            linhasDescobertas.add(i);
        }
        
        int qtdColunaCobreLinha[] = new int[listaLinha.length];
        Cromossomo cromossomo = new Cromossomo();
        
        while (!linhasDescobertas.isEmpty()){
            int random_pos = Util.getRandomInt(linhasDescobertas.size());
            int linha = linhasDescobertas.get(random_pos);
            
            ArrayList<Integer> conjuntoColuna = listaLinha[linha];
            int menorColuna = colunaMinimizaCusto(conjuntoColuna, linhasDescobertas, listaColuna, listaCusto);
            
            cromossomo.addColuna(menorColuna, listaCusto[menorColuna]);
            removerCobertos(qtdColunaCobreLinha, linhasDescobertas, listaColuna[menorColuna]);
        }
    }
    
    private static int colunaMinimizaCusto(ArrayList<Integer> conjuntoColuna, ArrayList<Integer> linhasDescobertas, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        double menor = Double.MAX_VALUE;
        int menorColuna = -1;
        for (int i = 0; i < conjuntoColuna.size(); i++) {
            int coluna = conjuntoColuna.get(i);
            double custo = listaCusto[coluna];
            int intersecao_size = Util.sizeIntersecao(linhasDescobertas, listaColuna[coluna]);
            if ((custo / intersecao_size) < menor) {
                menor = custo / intersecao_size;
                menorColuna = coluna;
            }
        }
        return menorColuna;
    }
    
    private static void removerCobertos(int[] qtdColunaCobreLinha, ArrayList<Integer> linhasDescobertas, ArrayList<Integer> conjuntoLinhas) {
        for (int i = 0; i < conjuntoLinhas.size(); i++) {
            int linha = conjuntoLinhas.get(i);
            qtdColunaCobreLinha[linha]++;
            linhasDescobertas.remove(new Integer(linha));
        }
    }
    
    public void eliminaRedundancia(){
        
    }
}
