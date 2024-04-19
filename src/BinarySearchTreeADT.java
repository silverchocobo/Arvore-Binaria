public interface BinarySearchTreeADT <K,V> {

    //Interface com os métodos a serem utilizados
    public V buscar(K key);
    public void inserir(K key, V value);
    public boolean excluir(K key);
    public void preOrdem();
    public void emOrdem();
    public void posOrdem();
}
