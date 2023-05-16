package br.ufpe.cin.residencia.banco;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaRepository;

//Ver anotações TODO no código
public class BancoViewModel extends AndroidViewModel {
    private ContaRepository repository;
    Double saldoTotal = 0.0;
    public MutableLiveData<List<Conta>> _listaAtual = new MutableLiveData<>();
    public LiveData<List<Conta>> contas = _listaAtual;

    public BancoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
    }
     void transferir(String numeroContaOrigem, String numeroContaDestino, double valor) { //método para transferir entre contas
        //TODO 11. implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
        new Thread(() -> { //cria uma nova thread
            Conta contaOrigem = repository.buscarPeloNumero(numeroContaOrigem); //busca a conta de origem pelo número
            Conta contaDestino = repository.buscarPeloNumero(numeroContaDestino); //busca a conta de destino pelo número
            contaOrigem.debitar(valor); //debita o valor da conta de origem
            contaDestino.creditar(valor); //credita o valor na conta de destino
            repository.atualizar(contaOrigem); //atualiza a conta de origem
            repository.atualizar(contaDestino); //atualiza a conta de destino
        }).start(); //inicia a thread

    }

    void creditar(String numeroConta, double valor) { //método para creditar em conta
        //TODO 11. implementar creditar em conta (lembrar de salvar no BD o objeto Conta modificado)
        new Thread(() -> { //cria uma nova thread
            Conta conta = repository.buscarPeloNumero(numeroConta); //busca a conta pelo número
            conta.creditar(valor); //credita o valor na conta
            repository.atualizar(conta); //atualiza a conta
        }).start(); //inicia a thread
    }

    void debitar(String numeroConta, double valor) { //método para debitar em conta
        //TODO 11. implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
        new Thread(() -> { //cria uma nova thread
            Conta conta = repository.buscarPeloNumero(numeroConta); //busca a conta pelo número
            conta.debitar(valor); //debita o valor da conta
            repository.atualizar(conta); //atualiza a conta
        }).start(); //inicia a thread
    }

    void buscarPeloNome(String nomeCliente) {
        //TODO 11. implementar busca pelo nome do Cliente
        new Thread(() -> {
            List<Conta> listContas = repository.buscarPeloNome(nomeCliente);
            _listaAtual.postValue(listContas);
        }).start();
    }

    void buscarPeloCPF(String cpfCliente) {
        //TODO 11. implementar busca pelo CPF do Cliente
        new Thread(() -> {
            List<Conta> listContas = repository.buscarPeloCPF(cpfCliente);
            _listaAtual.postValue(listContas);
        }).start();
    }

    void buscarPeloNumero(String numeroConta) {
        //TODO 11. implementar busca pelo número da Conta
        new Thread(() -> {
            Conta conta = repository.buscarPeloNumero(numeroConta);
            List<Conta> listContas = Arrays.asList(conta);
            _listaAtual.postValue(listContas);
        }).start();
    }

    void saldoTotal() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                saldoTotal = BancoDB.getDB(getApplication()).contaDAO().saldoTotal();
            }
        });
        thread.start();
    }


    //metodo para obter o saldo total de todas as contas
//    public LiveData<Double> getSaldoTotal() {
//        MutableLiveData<Double> saldoTotal = new MutableLiveData<>();
//        new Thread(() -> saldoTotal.postValue(repository.saldoTotal())).start();
//        return saldoTotal;
    public Double getSaldoTotal() {
        saldoTotal();
        if (saldoTotal == null) {
            saldoTotal = 0.0;


        }
        return saldoTotal;
    }

}
