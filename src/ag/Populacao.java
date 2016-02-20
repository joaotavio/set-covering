package ag;

import java.util.ArrayList;

public class Populacao {
    
    private int tam_populacao;
    private Cromossomo[] populacao;

    public Populacao(int tam_populacao) {
        this.tam_populacao = tam_populacao;
        populacao = new Cromossomo[tam_populacao];
    }
    
    public void gerarPopulacaoInicial(ArrayList<Integer>[] listaLinha, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        for (int i = 0; i < tam_populacao; i++) {
            Cromossomo c = new Cromossomo();
            c.gerarIndividuo(listaLinha, listaColuna, listaCusto);
            c.eliminaRedundancia(listaColuna);
            populacao[i] = c;
        }
    }
}
