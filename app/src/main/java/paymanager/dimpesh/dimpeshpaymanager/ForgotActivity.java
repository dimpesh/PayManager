package paymanager.dimpesh.dimpeshpaymanager;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    private Typeface roboto;
    TextView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        setTitle("Forgot Password - Money Manager");
        roboto= Typeface.createFromAsset(getApplicationContext().getAssets(),"Roboto-Black.ttf");
        head=(TextView)findViewById(R.id.forgot_head);

        head.setTypeface(roboto);

    }

    public void sendPassword(View v)
    {
        Toast.makeText(getApplicationContext(),"Password has been sent to registered mail id...",Toast.LENGTH_LONG).show();
    }
}
