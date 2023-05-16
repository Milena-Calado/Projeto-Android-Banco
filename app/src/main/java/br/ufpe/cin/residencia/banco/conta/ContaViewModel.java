package br.ufpe.cin.residencia.banco.conta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.ufpe.cin.residencia.banco.BancoDB;

//Ver métodos anotados com TODO
public class ContaViewModel extends AndroidViewModel {

    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;

    public ContaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = repository.getContas();
    }

    void inserir(Conta c) {
        new Thread(() -> repository.inserir(c)).start();
    }

    void atualizar(Conta c) {
        //TODO 7. implementar
        new Thread(() -> //cria uma nova thread
                repository.atualizar(c)).start(); //nova thread para atualizar a conta que chama o método atualizar da classe ContaRepository

    }

    void remover(Conta c) {
        //TODO 7. implementar
        new Thread(() -> repository.remover(c)).start(); //nova thread para remover a conta que chama o método remover da classe ContaRepository
    }

    void buscarPeloNumero(String numeroConta) {
        //TODO 7. implementar
        new Thread(() -> _contaAtual.postValue(repository.buscarPeloNumero(numeroConta))).start(); //nova thread para buscar a conta pelo número que chama o método buscarPeloNumero da classe ContaRepository
    }


}
