package volkodav.ampilogova.darya.projecte_github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private Implementacio implementacio = new Implementacio(this);
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // CREAM UN INTENT PER DESPRÉS PASSAR-LI EL ID DEL VI QUE HEM DE VISUALITZAR
        Intent i = getIntent();
        id = i.getStringExtra("ID");

        // SI EL ID DEL VI EXISTEIX...
        if (!id.equals("")) {

            // OBRIM LA BASE DE DADES
            implementacio.open();

            // PASSEM EL ID DEL VI
            Vi vi = implementacio.getVi(Long.valueOf(id));

            // CRIDEM AL MÈTODE
            llegirVi(vi);

            // TANQUEM LA BASE DE DADES
            implementacio.close();
        }
        // SI EL ID NO COINCIDEIX, CRIDAREM AL MÈTODE PER CREAR UN NOU VI
        creacioVi();
    }

    private void creacioVi() {
        // EN CLICAR EL BOTÓ DE OK, CRIDAREM AL MÈTODE DE GUARDAR VI
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

    // MÈTODE PER PODER VEURE LES DADES DEL VI, EN CLICAR DAMUNT ELL
    public void llegirVi(Vi vi) {

        implementacio.open();

        EditText nomVi = findViewById(R.id.t_nomVi);
        Spinner tipus = findViewById(R.id.sp_tipus);
        EditText graduacio = findViewById(R.id.t_graduacio);
        AutoCompleteTextView bodega = findViewById(R.id.t_bodega);
        EditText data = findViewById(R.id.t_data);

        nomVi.setText(vi.getNomVi());
        montaSpinners(vi.getTipus());
        graduacio.setText(vi.getGraduacio());
        montaAutocompleta(vi.getIdBodega());
        data.setText(vi.getData());

        implementacio.close();
    }

    // CREAM EL MÈTODE GUARDAR VI PER A PODER GUARDAR EL QUE HEM INSTRODUÏT DINS LES CAIXES
    public void guardaVi() {

        // OBRIM LA BASE DE DADES
        implementacio.open();

        Vi vi = new Vi();

        EditText nomVi = findViewById(R.id.t_nomVi);
        Spinner tipus = findViewById(R.id.sp_tipus);
        EditText graduacio = findViewById(R.id.t_graduacio);
        AutoCompleteTextView bodega = findViewById(R.id.t_bodega);
        EditText data = findViewById(R.id.t_data);

        vi.setNomVi(nomVi.getText().toString());
        montaSpinners(tipus.getSelectedItem().toString());
        //vi.setTipus(tipus.getSelectedItem().toString());
        vi.setGraduacio(graduacio.getText().toString());
        vi.setIdBodega(implementacio.findInsertBodegaPerNom(bodega.getText().toString()));
        vi.setData(data.getText().toString());



        // SI EL ID DEL VI QUE SE LI HA PASSAT NO EXISTEIX, ACTUALITZEM
        if (!id.equals("")) {
            vi.setId(Long.valueOf(id));
            //update
            implementacio.actualitzarVi(vi);

        // SINÓ CREAREM EL VI
        } else {
            implementacio.crearVi(vi);
        }
        // TANQUEM LA BASE DE DADES
        implementacio.close();
    }

    // MÈTODE ON S'OBLIGA AL USUARI A ELEGIR EL TIPUS DE VI
    private void montaSpinners(String t) {
        implementacio.open();
        List<String> llista;
        llista = implementacio.getAllTipus();
        Spinner spinner = findViewById(R.id.sp_tipus);

        // CREAM EL ADAPTER
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, llista);

        // DROP DOWN ESTIL – LLISTA AMB RADIO BUTTON
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // ASSIGNEM L'ADAPTER
        spinner.setAdapter(dataAdapter);
        if (t != null && !t.equals("")) {

            // SI HI HA UN VALOR ASSIGNAT ENS POSICIONEM
            selectValue(spinner,t);
        }
        implementacio.close();
    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    // FEIM UN AUTOCOMPLETE TEXT PER PODER ELEGIR ELS VALORS DE UNA LLISTA O INTRODUÏR-NE DE NOUS
    private void montaAutocompleta(long idBodega){

        String nomBodega;
        Bodega bodegues;

        implementacio.open();
        bodegues = implementacio.getBodega(idBodega);
        nomBodega = bodegues.getNomBodega();

        List<String> llista = implementacio.getAllBodega();
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, llista);
        AutoCompleteTextView bodega = findViewById(R.id.t_bodega);
        bodega.setThreshold(0);
        bodega.setAdapter(adapter);
        if (nomBodega!=null && !nomBodega.equals("")) {
            bodega.setText(nomBodega,true);
        }

        implementacio.close();
    }
}