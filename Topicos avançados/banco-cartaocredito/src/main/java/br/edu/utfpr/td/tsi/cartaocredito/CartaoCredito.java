package br.edu.utfpr.td.tsi.cartaocredito;

public class CartaoCredito {
	private int numero = 1;
	public void criarCartao(String cliente) {
		System.out.println("Cartão: " + numero + "criado para o cliente: " + cliente);
		numero++;
	}
}
