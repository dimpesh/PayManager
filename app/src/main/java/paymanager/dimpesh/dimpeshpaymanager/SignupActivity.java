package paymanager.dimpesh.dimpeshpaymanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void checkRegister(View v) {
        if (username.getText().toString().equals("null"))
            Toast.makeText(getApplicationContext(), "Improper Details...\nTryAgain", Toast.LENGTH_SHORT).show();
        else if (email.getText().toString().equals("null"))
            Toast.makeText(getApplicationContext(), "Improper Details...\nTryAgain", Toast.LENGTH_SHORT).show();
        else if (password.getText().toString().equals("null"))
            Toast.makeText(getApplicationContext(), "Improper Details...\nTryAgain", Toast.LENGTH_SHORT).show();
        else if (phone.getText().toString().equals("null"))
            Toast.makeText(getApplicationContext(), "Improper Details...\nTryAgain", Toast.LENGTH_SHORT).show();
        else if (amt.getText().toString().equals("null"))
            Toast.makeText(getApplicationContext(), "Improper Details...\nTryAgain", Toast.LENGTH_SHORT).show();

        else {
            int n = db.insertDB(username.getText().toString(), email.getText().toString(),
                    Float.parseFloat(amt.getText().toString()), phone.getText().toString(), password.getTransitionName().toString());
            if (n > 0)
                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getApplicationContext(), "Error in Signing Up \n Try Later...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void alreadyMember(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
