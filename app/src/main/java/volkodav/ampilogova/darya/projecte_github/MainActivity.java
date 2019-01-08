package volkodav.ampilogova.darya.projecte_github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /* EN CLICAR A UN ELEMENT DE LA LLISTA CRIDAREM A L'ACTIVITY DE EDITAR I PASSEM
            LA CLAU PRIMÀRIA */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view.findViewById(R.id.id)).getText().toString();
                Intent in = new Intent(getApplicationContext(), EditaVi.class);
                in.putExtra("ID", s);
                startActivity(in);
            }
        });

        Button btNou=(Button) findViewById(R.id.nouBtn);
        btNou.setOnClickListener(
                // Cridam l'activity d'edició indicant que es un insert (clau primària en blanc per exemple)
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getApplicationContext(),EditaVi.class);
                        in.putExtra("ID", "");
                        startActivity(in);
                    }
                }
        );
        mostraVins(); // Carrega la llista
    }
}
