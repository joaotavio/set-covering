package ag;

import java.util.ArrayList;

public class Cromossomo {
    
    //Lista de indices das colunas que pertencem a solução
    private ArrayList<Integer> solucao;
    private double custoTotal;

    public Cromossomo() {
        solucao = new ArrayList<>();
        custoTotal = 0;
    }
    
    public void addColuna(int index, double custo){
        solucao.add(index);
        custoTotal += custo;
    }
    
    public static void gerarIndividuo(ArrayList<Integer>[] listaLinha, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        //listaLinha = ALFA
        //listaColuna = BETA
        
        ArrayList<Integer> linhasDescobertas = new ArrayList<>();
        for (int i = 0; i < listaLinha.length; i++) {
            linhasDescobertas.add(i);
        }
        
        int w[] = new int[listaLinha.length];
        Cromossomo cromossomo = new Cromossomo();
        
        while (!linhasDescobertas.isEmpty()){
            int random_pos = Util.getRandomInt(linhasDescobertas.size());
            
            ArrayList<Integer> alfa = listaLinha[random_pos];
            double custo = listaCusto[alfa.get(0)];
            int intersecao_size = Util.sizeIntersecao(linhasDescobertas, listaColuna[alfa.get(0)]);
            double menor = custo / intersecao_size;
            int menorColuna = 0;
            for (int i = 1; i < alfa.size(); i++) {
                custo = listaCusto[alfa.get(i)];
                intersecao_size = Util.sizeIntersecao(linhasDescobertas, listaColuna[alfa.get(i)]);
                if ((custo / intersecao_size) < menor){
                    menor = custo / intersecao_size;
                    menorColuna = i;
                }
            }
            
            cromossomo.addColuna(menorColuna, listaCusto[menorColuna]);
            //wi
            System.out.println(linhasDescobertas);
            linhasDescobertas.removeAll(listaColuna[menorColuna]);
            
        }
    }
    
    public void eliminaRedundancia(){
        
    }
}
