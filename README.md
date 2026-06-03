# 🚀 JasmineScan

Um scanner de portas TCP desenvolvido em Java utilizando **Sockets**, **Threads**, **ExecutorService** e **Future** para realizar verificações concorrentes de portas em um host.

---

## 📖 Sobre o Projeto

Este projeto foi criado com o objetivo de estudar conceitos importantes de:

* Programação Orientada a Objetos (POO)
* Programação Concorrente
* Redes de Computadores
* Sockets TCP
* ExecutorService
* Future
* Multithreading

O scanner percorre todas as portas TCP de um host (1 até 65535) e verifica quais estão abertas.

Cada porta é testada em uma thread separada, tornando a execução muito mais rápida do que um scanner sequencial.

---

## ⚠️ Aviso

Este projeto foi desenvolvido para fins educacionais. (ou não...)

Utilize apenas em:

* Laboratórios próprios
* Máquinas virtuais
* Ambientes autorizados
* Redes que você possui permissão para testar

Nunca realize varreduras em sistemas de terceiros sem autorização.

---

## 🛠 Tecnologias Utilizadas

* Java 17
* TCP Sockets
* ExecutorService
* Future
* Collections Framework

---

## 📂 Estrutura do Projeto

```text
PortScanner
│
├── PortScan
│   ├── src
│   ├── Main
│   ├── PortScanner - Programa principal
│   ├── PortScan.iml
│   ├── .gitatributes
│   ├── LICENCE
│   └── REAME.md

```

---

## 🔥 Como Funciona

1. O usuário informa um endereço IP.
2. O programa cria um pool de threads.
3. Cada porta recebe uma tarefa de verificação.
4. O Socket tenta se conectar.
5. Se conectar:

   * Porta aberta.
6. Se falhar:

   * Porta fechada.
7. Os resultados são armazenados em Futures.
8. Ao final, todas as portas abertas são exibidas.

---

## ▶️ Executando

Compile:

```bash
javac PortScanner.java
```

Execute:

```bash
java PortScanner
```

Exemplo:

```text
Digite o IP:
192.168.0.1

22
80
443

Existem: 3 portas abertas na host 192.168.0.1
```

---

## 🧠 Conceitos Aprendidos

### Sockets

Permitem criar conexões TCP entre máquinas.

### Threads

Permitem executar várias tarefas simultaneamente.

### ExecutorService

Gerencia e reutiliza threads automaticamente.

### Future

Representa um resultado que ainda será produzido por uma thread.

### Callable

Semelhante ao Runnable, porém retorna um valor.

---

## 📈 Melhorias Futuras

* Scanner de intervalo de IPs
* Banner Grabbing
* Detecção de serviços
* Interface gráfica (JavaFX)
* Exportação para JSON
* Exportação para CSV
* Scanner UDP
* Definição personalizada de timeout
* Scanner de portas específicas
* Estatísticas de desempenho

---

## 🎯 Objetivo de Aprendizado

Este projeto não tem como foco competir com ferramentas profissionais como Nmap.

Seu principal objetivo é compreender:

* Como um port scanner funciona internamente.
* Como utilizar sockets em Java.
* Como trabalhar com concorrência.
* Como construir ferramentas próprias para segurança da informação.

---

## 👨‍💻 Autor - Gabriel / LGA1155dev

Desenvolvido como projeto de estudo para aprofundar conhecimentos em:

* Java
* Redes
* Linux
* Segurança da Informação
* Pentest
* Programação Concorrente


### Foto do projeto:

<img width="1440" height="867" alt="scanner" src="https://github.com/user-attachments/assets/752d666e-cfe3-4798-9faf-d4b4b9427083" />

