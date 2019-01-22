package volkodav.ampilogova.darya.projecte_github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private Implementacio i;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        creacioVi();
    }

    private void creacioVi() {

        Button botoAceptar = findViewById(R.id.boto_ok);
        botoAceptar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        guardaVi();
                        finish();
                    }
                });
    }

    public void guardaVi() {
        i = new Implementacio(this);
        i.open();

        Vi vi = new Vi();

        EditText nomVi = findViewById(R.id.t_nomVi);
        EditText tipus = findViewById(R.id.t_tipus);
        EditText graduacio = findViewById(R.id.t_graduacio);
        EditText data = findViewById(R.id.t_data);

        vi.setNomVi(nomVi.getText().toString());
        vi.setTipus(tipus.getText().toString());
        vi.setGraduacio(graduacio.getText().toString());
        vi.setData(data.getText().toString());

        if (!id.equals("")) {
            //update
            i.actualitzarVi(vi);
        } else {
            i.crearVi(vi);
        }

        i.close();
    }
}