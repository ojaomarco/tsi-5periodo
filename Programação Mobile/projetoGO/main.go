package main

import (
	"log"
	"math/rand"
	"net/http"

	"github.com/gorilla/mux"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/palavra", generateRandomWord).Methods("GET")
	log.Fatal(http.ListenAndServe(":8000", r))
}

func generateRandomWord(w http.ResponseWriter, r *http.Request) {
	words := []string{
		"maçã", "banana", "laranja", "abacaxi", "manga",
		"limão", "uva", "morango", "melancia", "pera",
	}

	randomIndex := rand.Intn(len(words))
	w.Header().Set("Content-Type", "text/plain")
	w.Write([]byte(words[randomIndex]))
}

// Importações de pacotes:

// O pacote log é usado para registrar mensagens de erro.
// O pacote math/rand é usado para gerar números aleatórios.
// O pacote net/http é usado para criar o servidor HTTP.
// O pacote github.com/gorilla/mux é um roteador HTTP que facilita a definição de rotas.
// A função main é a função de entrada do programa. Ela configura o roteador, define a rota /palavra para a função generateRandomWord e inicia o servidor HTTP.

// A variável randomIndex é gerada usando rand.Intn(len(words)), que retorna um número aleatório no intervalo [0, len(words)).

// O cabeçalho da resposta é configurado para Content-Type: text/plain para indicar que a resposta será um texto simples.

// A palavra aleatória correspondente ao índice gerado é escrita como resposta usando w.Write([]byte(words[randomIndex])).

// Na função main, o roteador é criado usando mux.NewRouter().

// A rota /palavra é definida usando r.HandleFunc("/palavra", generateRandomWord).Methods("GET"). Isso associa a função generateRandomWord à rota /palavra e especifica que ela deve ser tratada apenas para requisições GET.

// O servidor HTTP é iniciado usando http.ListenAndServe(":8000", r). Ele escuta na porta 8000 e roteia as requisições para o roteador r.

// Se ocorrer algum erro ao iniciar o servidor, log.Fatal é usado para registrar o erro e encerrar o programa.

// Em resumo, esse código cria um servidor HTTP que responde a requisições GET para a rota /palavra com uma palavra aleatória da lista predefinida
