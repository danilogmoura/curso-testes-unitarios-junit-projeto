package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carrinho de Compras")
class CarrinhoCompraTest {

    private CarrinhoCompra carrinhoCompra;
    private Cliente cliente;
    private List<ItemCarrinhoCompra> itens;
    private Produto notebook;
    private Produto desktop;
    private Produto tablet;

    @Nested
    @DisplayName("Dado um carinho instanciado com cliente null")
    class CarrinhoSemCliente {

        @BeforeEach
        void beforeEach() {
            cliente = null;

            notebook = new Produto(1L, "Notebook", "Notebook", BigDecimal.TEN);
            desktop = new Produto(2L, "Desktop", "Desktop", BigDecimal.valueOf(20.50));

            itens = new ArrayList<>();
            itens.add(new ItemCarrinhoCompra(notebook, 1));
            itens.add(new ItemCarrinhoCompra(desktop, 1));
        }

        @Nested
        @DisplayName("Quando instanciar um carrinho")
        class InstanciarCarrinho {

            @Test
            @DisplayName("Então deve lançar exception")
            void retornaUmaException() {
                assertThrows(NullPointerException.class, () -> new CarrinhoCompra(cliente, itens));
            }
        }
    }

    @Nested
    @DisplayName("Dado um carinho instanciado com itens null")
    class ListaDeItens {

        @BeforeEach
        void beforeEach() {
            cliente = new Cliente(1L, "Alex Silva");
            itens = null;
        }

        @Nested
        @DisplayName("Quando instanciar um carrinho")
        class InstanciarCarrinho {

            @Test
            @DisplayName("Então deve lançar exception")
            void retornaUmaException() {
                assertThrows(NullPointerException.class, () -> new CarrinhoCompra(cliente, itens));
            }
        }
    }

    @Nested
    @DisplayName("Dado um carrinho com dois itens")
    class ListaDeItensComProdutos {

        @BeforeEach
        void beforeEach() {
            cliente = new Cliente(1L, "Alex Silva");

            notebook = new Produto(1L, "Notebook", "Notebook", BigDecimal.TEN);
            desktop = new Produto(2L, "Desktop", "Desktop", BigDecimal.valueOf(20.50));
            tablet = new Produto(3L, "Tablet", "Tablet", BigDecimal.valueOf(30.50));

            itens = new ArrayList<>();

            itens.add(new ItemCarrinhoCompra(notebook, 2));
            itens.add(new ItemCarrinhoCompra(desktop, 1));

            carrinhoCompra = new CarrinhoCompra(cliente, itens);
        }

        @Nested
        @DisplayName("Quando retornar itens")
        class RetornarItens {

            @Test
            @DisplayName("Então deve retornar dois itens")
            void retornaDoisItens() {
                assertEquals(2, carrinhoCompra.getItens().size());
            }

            @Test
            @DisplayName("E deve retornar uma nova instância da lista de itens")
            void retornaNovaInstanciaDeItens() {
                carrinhoCompra.getItens().clear();
                assertEquals(2, carrinhoCompra.getItens().size());
            }
        }

        @Nested
        @DisplayName("Quando remover um notebook")
        class QuandoRemoverUmItem {

            @BeforeEach
            void beforeEach() {
                carrinhoCompra.removerProduto(notebook);
            }

            @Test
            @DisplayName("Então deve diminuir a quantidade total de itens")
            void dimiuQuantidadeDeItens() {
                assertEquals(1, carrinhoCompra.getItens().size());
            }

            @Test
            @DisplayName("E não deve remover demais itens")
            void naoDeveRemoverDemaisItens() {
                assertEquals(desktop, carrinhoCompra.getItens().get(0).getProduto());
            }
        }

        @Nested
        @DisplayName("Quando aumentar quantidade de um notebook")
        class QuandoAumentarQuantidade {

            @BeforeEach
            void beforeEach() {
                carrinhoCompra.aumentarQuantidadeProduto(notebook);
            }

            @Test
            @DisplayName("Então deve somar a quantidade de itens iguais")
            void deveSomarAQuantidadeDeItensIguais() {
                assertEquals(3, carrinhoCompra.getItens().get(0).getQuantidade());
                assertEquals(1, carrinhoCompra.getItens().get(1).getQuantidade());
            }

            @Test
            @DisplayName("Então deve retornar quatro de quantidade total de itens")
            void deveRetornarQuatroDeQUantidadeTotalDeItens() {
                assertEquals(4, carrinhoCompra.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("Então deve retornar o valor total correto de itens")
            void deveRetornarOValorTotaCorretoDeItens() {
                assertEquals(new BigDecimal("50.5"), carrinhoCompra.getValorTotal());
            }
        }

        @Nested
        @DisplayName("Quando diminuir a quantidade de um item com apenas um de quantidade")
        class QuandoDiminuirQuantidadeDeItemUnico {

            @BeforeEach
            void beforeEach() {
                carrinhoCompra.diminuirQuantidadeProduto(desktop);
            }

            @Test
            @DisplayName("Então deve remover item")
            void deveRemoverItem() {
                assertNotEquals(carrinhoCompra.getItens().get(0).getProduto(), desktop);
            }
        }

        @Nested
        @DisplayName("Quando adicionar item com quantidade inválida")
        class QuandoAdicionarItemComQuantidadeInvalida {

            @Test
            @DisplayName("Então deve lançar exception")
            void entaoDeveFalhar() {
                assertThrows(RuntimeException.class, () -> carrinhoCompra.adicionarProduto(desktop, -1));
            }
        }

        @Nested
        @DisplayName("Quando esvaziar carrinho")
        class QuandoEsvaziarCarrinho {

            @BeforeEach
            void beforeEach() {
                carrinhoCompra.esvaziar();
            }

            @Test
            @DisplayName("Então deve somar a quantidade de itens iguais")
            void deveSomarAQuantidadeDeItensIguais() {
                assertEquals(0, carrinhoCompra.getItens().size());
            }

            @Test
            @DisplayName("Então deve retornar ZERO de quantidade total de itens")
            void deveRetornarZeroNoTotalDeItens() {
                assertEquals(0, carrinhoCompra.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("Então deve retornar ZERO no valor total de itens")
            void deveRetornarZeroNoValorTotalDeItens() {
                assertEquals(BigDecimal.ZERO, carrinhoCompra.getValorTotal());
            }

        }
    }


    @Nested
    @DisplayName("Dado um carrinho vazio")
    class DadoUmCarrinhoVazio {

        @BeforeEach
        void beforeEach() {
            cliente = new Cliente(1L, "Alex Silva");

            notebook = new Produto(1L, "Notebook", "Notebook", BigDecimal.TEN);
            desktop = new Produto(2L, "Desktop", "Desktop", BigDecimal.valueOf(20.50));
            tablet = new Produto(3L, "Tablet", "Tablet", BigDecimal.valueOf(30.50));

            itens = new ArrayList<>();

            carrinhoCompra = new CarrinhoCompra(cliente, itens);
        }

        @Nested
        @DisplayName("Quando adicionar dois notebooks iguais e um desktop")
        class QuandoAdicionarDoisItensIguais {

            @BeforeEach
            void beforeEach() {
                carrinhoCompra.adicionarProduto(notebook, 1);
                carrinhoCompra.adicionarProduto(notebook, 1);
                carrinhoCompra.adicionarProduto(desktop, 1);
            }

            @Test
            @DisplayName("Então deve somar na quantidade dos itens iguais")
            void entaoDeveSomarNaQuantidade() {
                assertEquals(2, carrinhoCompra.getItens().get(0).getQuantidade());
                assertEquals(1, carrinhoCompra.getItens().get(1).getQuantidade());
            }

            @Test
            @DisplayName("E retornar três na quantidade total de itens")
            void quantidadeTotalDeItens() {
                assertEquals(3, carrinhoCompra.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("E retornar valor total correto de itens")
            void retornarValorTotalItens() {
                assertEquals(new BigDecimal("40.5"), carrinhoCompra.getValorTotal());
            }
        }
    }
}


