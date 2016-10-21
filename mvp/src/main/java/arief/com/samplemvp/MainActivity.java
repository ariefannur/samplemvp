package arief.com.samplemvp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import arief.com.samplemvp.model.Result;
import arief.com.samplemvp.model.User;

public class MainActivity extends AppCompatActivity implements Mvp.LoginView {

    private EditText username, password;
    private Button btnLogin;
    private LoginPresenter mLoginPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        mLoginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.doLogin(username.getText().toString(), password.getText().toString());
            }
        });

    }

    @Override
    public void onError(Result result) {
        Snackbar.make(findViewById(R.id.activity_main), result.message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        Snackbar.make(findViewById(R.id.activity_main), "Selamat Datang", Snackbar.LENGTH_SHORT).show();
    }
}
