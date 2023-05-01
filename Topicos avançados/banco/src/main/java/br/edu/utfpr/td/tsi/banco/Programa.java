package br.edu.utfpr.td.tsi.banco;

import br.edu.utfpr.td.tsi.cartaocredito.CartaoCredito;
import br.edu.utfpr.td.tsi.cliente.GerenciadorClientes;

public class Programa {
	public static void main(String[] args) {
		GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
		gerenciadorClientes.cadastrarCliente("João");
		
		CartaoCredito cartao = new CartaoCredito();
		cartao.criarCartao("João");
	}
}
