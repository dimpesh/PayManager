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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Users users;
    EditText name, pass;
    private Typeface roboto;
    TextView head;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roboto = Typeface.createFromAsset(getApplicationContext().getAssets(), "Roboto-Black.ttf");
        head = (TextView) findViewById(R.id.textView_head);

        head.setTypeface(roboto);

        name = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        db = DBAdapter.getDBAdapter(getApplicationContext());
        if (db.checkDatabase() == false)
            db.createDatabase(getApplicationContext());

        db.openDatabase();
    }

    public void signup(View v) {
//        Toast.makeText(getApplicationContext()," New User Registration",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }

    public void forgot(View v) {
//        Toast.makeText(getApplicationContext(),"Forgot Password",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ForgotActivity.class));

    }

    public void signIn(View v) {
        // TODO : change the users calling to dynamic names...(presently HardCoded)...

        String sname = name.getText().toString();
        String spass = pass.getText().toString();

        users=db.getRegisterData(sname, spass);

        if(users!=null)
        {
            Toast.makeText(getApplicationContext(),getString(R.string.welcome_signin)+"\n"+users.getUsername(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),getString(R.string.error_signin)+"\n\tTry Again",Toast.LENGTH_SHORT).show();

        }


    }
}
