import java.io.FileWriter;
import java.io.IOException;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {

    protected Nodo raiz;

    //Classe aninhada com nodos com atributos do tipo chave/valor.
    protected class Nodo {
        private K key;
        private V value;
        private Nodo esquerda, direita;

        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }

        //Utiliza função comparable para definir qual o próximo nodo a ser buscado.
        public Nodo next(K other){
            return other.compareTo(key) < 0 ? esquerda : direita;
        }


        @Override
        public String toString(){
            return "" + key;
        }


    }


    @Override
    public V buscar(K key) {
        return buscar(raiz, key);
    }

    //Método de busca que funciona de forma recursiva e utilizando o método next.
    private V buscar(Nodo Nodo, K key){
        if (Nodo == null){
            System.out.println("Nodo não encontrado");
            return null;

        }else if (key.compareTo(Nodo.key) == 0){
            System.out.println("Nodo encontrado! ");
            return Nodo.value;

        }
        return buscar(Nodo.next(key),key);
    }

    @Override
    public void inserir(K key, V value) {
        raiz = inserir(raiz, key, value);
    }

    //Método de inserção que leva em conta se o número é maior ou menor conforme a comparação recursiva.
    private Nodo inserir(Nodo Nodo, K key, V value){
        if (Nodo == null){
            return new Nodo (key, value);
        }
        else if (key.compareTo(Nodo.key) > 0){
            Nodo.direita = inserir(Nodo.direita, key, value);
        }
        else if (key.compareTo(Nodo.key) < 0){
            Nodo.esquerda = inserir(Nodo.esquerda, key, value);
        }

        return Nodo;
    }

    @Override
    public boolean excluir(K key) {
        return excluirPorCopia(key);
    }

    //Exclusão que ocorre através do método de cópia, tanto se o nodo tiver apenas um filho ou dois.
    private boolean excluirPorCopia(K key){
        Nodo parent = null, current = raiz;
        for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key));

        if(current == null) {
            return false;
        }
        else if (current.esquerda != null && current.direita != null){
            //Se o nó possui dois filhos
            Nodo tmp = current.esquerda;
            while(tmp.direita != null) tmp = tmp.direita;
            excluirPorCopia(tmp.key);
            current.key = tmp.key;
            }
        else{
            //Se o nó possui nenhum ou um filho
            Nodo nextNodo = current.direita == null ? current.esquerda : current.direita;
            if (current.equals(raiz)) raiz = nextNodo;
            else if (parent.esquerda.equals(current)) parent.esquerda = nextNodo;
            else parent.direita = nextNodo;
        }
        return true;
    }

    @Override
    public void preOrdem() {
        preOrdem(raiz);

    }

    //Imprime elementos da árvore no modo pré-ordem
    private void preOrdem(Nodo Nodo){
        if (Nodo != null){
            System.out.print(Nodo + " ");
            preOrdem(Nodo.esquerda);
            preOrdem(Nodo.direita);
        }
    }

    @Override
    public void emOrdem() {
        emOrdem(raiz);
    }

    //Imprime elementos da árvore em ordem
    private void emOrdem(Nodo Nodo){
        if(Nodo != null){
            emOrdem(Nodo.esquerda);
            System.out.print(Nodo + " ");
            emOrdem(Nodo.direita);
        }
    }

    @Override
    public void posOrdem() {
        posOrdem(raiz);
    }

    //Imprime elementos da árvore no modo pós-ordem.
    private void posOrdem(Nodo Nodo){

        if(Nodo!= null) {
            posOrdem(Nodo.esquerda);
            posOrdem(Nodo.direita);
            System.out.print(Nodo + " ");
        }
    }

    //Método para gerar arquivo .dot.
    public void gerarArqDot(String filename){
        if (filename == null || filename.isEmpty()) {
            filename = "arvoreBinGerado.dot";
        }
        try{
            FileWriter arqSaida = new FileWriter(filename);
            arqSaida.write("digraph ArvoreBin {\n");
            escreverPreOrdemDot(raiz,arqSaida);
            arqSaida.write("}\n");
            arqSaida.close();
            System.out.println("\nArquivo DOT gerado com sucesso: " + filename);

        }catch (IOException e){
            System.out.println("Erro ao gerar arquivo DOT: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Método para escrever árvore em Pré-Ordem no arquivo dot.
    private void escreverPreOrdemDot(Nodo raiz, FileWriter arqSaida) throws IOException{
        if (raiz != null){
            arqSaida.write(raiz.key + ";\n");

            if (raiz.esquerda != null)
                arqSaida.write(raiz.key + " -> " + raiz.esquerda.key + ";\n");

            if (raiz.direita != null)
                arqSaida.write(raiz.key + " -> " + raiz.direita.key + ";\n");
            escreverPreOrdemDot(raiz.esquerda, arqSaida);
            escreverPreOrdemDot(raiz.direita, arqSaida);
        }
    }



}
