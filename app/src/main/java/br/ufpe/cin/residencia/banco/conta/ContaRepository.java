package br.ufpe.cin.residencia.banco.conta;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import java.util.List;

//Ver anotações TODO no código
public class ContaRepository {
    private ContaDAO dao;
    private LiveData<List<Conta>> contas;

    public ContaRepository(ContaDAO dao) {
        this.dao = dao;
        this.contas = dao.contas();
    }

    public LiveData<List<Conta>> getContas() {// método para retornar a lista de contas
        return contas; //retorna a lista de contas
    }

    @WorkerThread
    public void inserir(Conta c) {
        dao.adicionar(c);
    } //método para inserir conta

    @WorkerThread
    public void atualizar(Conta c) { //método para atualizar conta
        //TODO 6.implementar atualizar
        dao.atualizar(c); //chama o método atualizar da classe ContaDAO
    }

    @WorkerThread
    public void remover(Conta c) { //método para remover conta
        //TODO 6. implementar remover
        dao.remover(c); //chama o método remover da classe ContaDAO
    }
    public Double saldoTotal(){ //método para calcular o saldo total
        if (dao.saldoTotal() == null) return 0.0; //se o saldo total for nulo, retorna 0
        return dao.saldoTotal(); } //chama o método saldoTotal da classe ContaDAO

    @WorkerThread
    public List<Conta> buscarPeloNome(String nomeCliente) { //método para buscar pelo nome do cliente
        //TODO 6. implementar busca
        return dao.buscarPeloNome(nomeCliente); //chama o método buscarPeloNome da classe ContaDAO
    }

    @WorkerThread
    public List<Conta> buscarPeloCPF(String cpfCliente) { //método para buscar pelo CPF do cliente
        //TODO 6. implementar busca
        return dao.buscarPeloCPF(cpfCliente); //chama o método buscarPeloCPF da classe ContaDAO
    }

    @WorkerThread
    public Conta buscarPeloNumero(String numeroConta) { //método para buscar pelo número da conta
        //TODO 6. implementar busca
        return dao.buscarPeloNumero(numeroConta); //chama o método buscarPeloNumero da classe ContaDAO
    }



}
