package br.ufpe.cin.residencia.banco.conta;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class ContaViewHolder  extends RecyclerView.ViewHolder {
    TextView nomeCliente = null;
    TextView infoConta = null;
    ImageView icone = null;

    public ContaViewHolder(@NonNull View linha) {
        super(linha);
        this.nomeCliente = linha.findViewById(R.id.nomeCliente);
        this.infoConta = linha.findViewById(R.id.infoConta);
        this.icone = linha.findViewById(R.id.icone);
    }
    //TODO 2. configurar e exibir informações de uma instância de Conta em um elemento de interface do usuário do RecyclerView,
    // como nome do cliente, número da conta, saldo e ícone
    void bindTo(Conta c) { // este é o cabeçalho do método, que declara que ele é um método que não retorna nada (void) e que recebe uma instância da classe Conta como parâmetro.
        this.nomeCliente.setText(c.nomeCliente); //definindo o texto do campo de nome do cliente com o valor nomeCliente da instância de Conta
        this.infoConta.setText(c.numero + " | " + "Saldo atual: " + NumberFormat.getCurrencyInstance().format(c.saldo)); //definindo o texto do campo de informações da conta com uma string formatada que inclui o número da conta e o saldo atual, formatado como moeda brasileira (R$).
        if (c.saldo <= 0) { //verificando se o saldo da conta é menor ou igual a zero. Se for, definimos o ícone como um ícone de exclusão, caso contrário, definimos o ícone como um ícone de confirmação.
            this.icone.setImageResource(R.drawable.delete);
        } else {
            this.icone.setImageResource(R.drawable.ok);
        }
        this.addListener(c.numero);
    }

    public void addListener(String numeroConta) {
        this.itemView.setOnClickListener(
                v -> {
                    Context c = this.itemView.getContext();
                    Intent i = new Intent(c, EditarContaActivity.class);
                    //TODO 3. cria uma nova Intent com uma informação extra contendo o número da conta e inicia uma nova atividade no Android.
                    // Essa nova atividade usará a informação para exibir detalhes da conta como nome, cpf e saldo.
                    i.putExtra("numeroDaConta", numeroConta); // adicionando uma informação extra à Intent i. O primeiro parâmetro é uma chave "numeroDaConta" que identifica essa informação e o segundo parâmetro é a variável numeroConta, que é um número de conta específico que será passado para a próxima atividade.
                    c.startActivity(i); // iniciando a nova atividade passando a Intent i como parâmetro. A variável c é um objeto Context que é usado para iniciar uma nova atividade.
                }
        );
    }
}
