package com.algaworks.junit.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarrinhoCompra {

    private final Cliente cliente;
    private List<ItemCarrinhoCompra> itens;

    public CarrinhoCompra(Cliente cliente) {
        this(cliente, new ArrayList<>());
    }

    public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(itens);
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
    }

    public List<ItemCarrinhoCompra> getItens() {
        return new ArrayList<>(itens);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        Objects.requireNonNull(produto);

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser igual ou maior que 1");
        }

        encontrarItemPeloProduto(produto)
                .ifPresentOrElse(
                        item -> item.adicionarQuantidade(quantidade),
                        () -> itens.add(new ItemCarrinhoCompra(produto, quantidade))
                );
    }

    public void removerProduto(Produto produto) {
        Objects.requireNonNull(produto);
        var item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        this.itens.remove(item);
    }

    private Optional<ItemCarrinhoCompra> encontrarItemPeloProduto(Produto produto) {
        return this.itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
    }

    public void aumentarQuantidadeProduto(Produto produto) {
        Objects.requireNonNull(produto);
        var item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        item.adicionarQuantidade(1);
    }

    public void diminuirQuantidadeProduto(Produto produto) {
        //TODO parâmetro não pode ser nulo, deve retornar uma exception
        //TODO caso o produto não exista, deve retornar uma exception
        //TODO deve diminuir em um quantidade do produto, caso tenha apenas um produto, deve remover da lista
    }

    public BigDecimal getValorTotal() {
        return itens.stream()
                       .map(ItemCarrinhoCompra::getValorTotal)
                       .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getQuantidadeTotalDeProdutos() {
        return itens.stream()
                       .mapToInt(ItemCarrinhoCompra::getQuantidade)
                       .sum();
    }

    public void esvaziar() {
        itens = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoCompra that = (CarrinhoCompra) o;
        return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens, cliente);
    }
}