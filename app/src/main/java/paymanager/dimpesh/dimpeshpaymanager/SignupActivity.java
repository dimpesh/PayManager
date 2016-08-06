package paymanager.dimpesh.dimpeshpaymanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Typeface roboto;
    TextView regHead;
    DBAdapter db;
    EditText username, email, phone, amt, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Registration - Money manager");
        roboto = Typeface.createFromAsset(getApplicationContext().getAssets(), "Roboto-Black.ttf");
        regHead = (TextView) findViewById(R.id.register_head);
        regHead.setTypeface(roboto);

        username = (EditText) findViewById(R.id.register_name);
        email = (EditText) findViewById(R.id.register_email);
        phone = (EditText) findViewById(R.id.register_phone);
        amt = (EditText) findViewById(R.id.register_initialamt);
        password = (EditText) findViewById(R.id.register_password);

        db = DBAdapter.getDBAdapter(getApplicationContext());
        if (db.checkDatabase() == false) {
            db.createDatabase(getApplicationContext());
        }
        db.openDatabase();
    }


    public void register(View v) {
        Log.v("My String : register ", "register is called");
        if (username.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Username cannot be empty...", Toast.LENGTH_SHORT).show();
        else if (email.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Email cannot be empty...", Toast.LENGTH_SHORT).show();
        else if (password.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Password cannot be empty...", Toast.LENGTH_SHORT).show();
        else if (phone.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Phone No. cannot be empty...", Toast.LENGTH_SHORT).show();
        else {

            // TODO Initially checking the cases only rewrite the Database part...
            Toast.makeText(getApplicationContext(),"All fields filled...",Toast.LENGTH_SHORT).show();
        }
    }

    public void alreadyMember(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
