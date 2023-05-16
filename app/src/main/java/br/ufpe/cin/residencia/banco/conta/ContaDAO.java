package br.ufpe.cin.residencia.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Ver anotações TODO no código
@Dao
public interface ContaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);

    //TODO 5.incluir métodos para atualizar conta e remover conta
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void atualizar(Conta c); //método para atualizar conta

    @Delete
    void remover(Conta c); //método para remover conta

       //TODO 5. incluir métodos para buscar pelo (1) número da conta, (2) pelo nome e (3) pelo CPF do Cliente
    @Query("SELECT * FROM contas WHERE numero = :numeroConta") //método para buscar pelo número da conta usando
    Conta buscarPeloNumero(String numeroConta);

    @Query("SELECT * FROM contas WHERE nomeCliente = :nomeCliente") //método para buscar pelo nome do cliente
    List<Conta> buscarPeloNome(String nomeCliente);

    @Query("SELECT * FROM contas WHERE cpfCliente = :cpfCliente") //método para buscar pelo CPF do cliente
    List<Conta> buscarPeloCPF(String cpfCliente);

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();

    @Query("SELECT SUM(saldo) FROM contas") //método para calcular o saldo total
    Double saldoTotal(); //chama o método saldoTotal


}
