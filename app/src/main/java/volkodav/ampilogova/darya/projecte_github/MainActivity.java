package volkodav.ampilogova.darya.projecte_github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /* EN CLICAR A UN ELEMENT DE LA LLISTA CRIDAREM A L'ACTIVITY DE EDITAR I PASSEM
            LA CLAU PRIMÀRIA */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view.findViewById(R.id.text_id)).getText().toString();
                Intent in = new Intent(getApplicationContext(), EditActivity.class);
                in.putExtra("ID", s);
                startActivity(in);
            }
        });

        Button btNou = findViewById(R.id.boto_afegir);
        btNou.setOnClickListener(

        // CRIDEM A L'ACTIVITY DE EDICIÓ INDICANT QUE ÉS UN INSERT (EX: CLAU PRIMÀRIA EN BLANC)
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getApplicationContext(),EditActivity.class);
                        in.putExtra("ID", "");
                        startActivity(in);
                    }
                }
        );
        // CARREGUEM LA LLISTA DE VINS
        mostraVins();
    }

    public void mostraVins() {
        // OBRIM LA BASE DE DADES
        Implementacio bd_vi = new Implementacio(this);
        bd_vi.open();

        // OBTENIM TOTS ELS VINS
        List<Vi> llistaVins = bd_vi.getAllVi();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < llistaVins.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            Vi vi = llistaVins.get(i);
            map.put("id", String.valueOf(vi.getId()));
            map.put("nomVi", vi.getNomVi());
            map.put("tipus", vi.getTipus());
            map.put("graduacio", vi.getGraduacio());
            map.put("data", vi.getData());
            llista.add(map);
        }

        // TANCAM LA BASE DE DADES
        bd_vi.close();

        // ASSIGNEM A LA LLISTA
        adapter = new SimpleAdapter(this, llista, R.layout.llista,
                new String[]{"id", "nomVi", "tipus", "graduacio", "data"},
                new int[]{R.id.text_id, R.id.text_nomVi, R.id.text_tipus, R.id.text_graduacio,
                        R.id.text_data});
        lv.setAdapter(adapter);
    }

    // QUAN TORNEM A L'ACTIVITAT PRINCIPAL, TORNAM A CARREGAR LA LLISTA, ON S'HA MODIFICAT
    @Override
    public void onResume() {
        super.onResume();
        mostraVins();
    }
}