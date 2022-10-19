package br.ubral.sistemadecadastro;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ubral.sistemadecadastro.MainActivity;

public class TelaListagemUsuarios {
    MainActivity act;
    TelaPrincipal tela_principal;
    Button btanterior, btproximo, btfechar;
    TextView txtnome, txtendereco, txttelefone, txtstatus;
    int index;

    public TelaListagemUsuarios(
            MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
        index = 0;
    }

    public void CarregarTela() {
//Antes de carregar a tela, verifica se existe registros
//inseridos
        if (act.getRegistros().size() == 0) {
            (new AlertDialog.Builder(act)).setTitle("Aviso")
                    .setMessage("Não existe nenhum registro cadastrado.")
                    .setNeutralButton("OK", null).show();
            return;
        }
        act.setContentView(R.layout.tela_listagem);
        btanterior = (Button) act.findViewById(R.id.btnAnterior);
        btproximo = (Button) act.findViewById(R.id.btnProximo);
        btfechar = (Button) act.findViewById(R.id.btnFechar);
        txtnome = (TextView) act.findViewById(R.id.txtNomeLista);
        txtendereco = (TextView) act.findViewById(R.id.txtEnderecoLista);
        txttelefone = (TextView) act.findViewById(R.id.txtTelefoneLista);
        txtstatus = (TextView) act.findViewById(R.id.texto);
        PreencheCampos(index);
        AtualizaStatus(index);
        btanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    index--;

                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < act.getRegistros().size() - 1) {
                    index++;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_principal.CarregarTela();
            }
        });
    }

    private void PreencheCampos(int idx) {
        txtnome.setText(act.getRegistros().get(idx).getNome());
        txtendereco.setText(act.getRegistros().get(idx).getEndereco());
        txttelefone.setText(act.getRegistros().get(idx).getTelefone());
    }

    private void AtualizaStatus(int idx) {
        int total = act.getRegistros().size();
        txtstatus.setText("Registros: " + (idx + 1) + "/" + total);
    }
}

