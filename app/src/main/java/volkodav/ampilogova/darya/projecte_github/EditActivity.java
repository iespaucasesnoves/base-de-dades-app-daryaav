package volkodav.ampilogova.darya.projecte_github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button botoAceptar = (Button)findViewById(R.id.boto_ok);
        botoAceptar.setOnClickListener(
                new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Vi vi = new Vi();
            }
        });
    }
}