package arief.com.samplemvvm;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import arief.com.samplemvvm.databinding.ActivityMainBinding;
import arief.com.samplemvvm.model.Result;
import arief.com.samplemvvm.model.User;


public class MainActivity extends AppCompatActivity implements ViewLogin {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModel model = new ViewModel(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(model);

    }

    @Override
    public void onError(Result result) {

        Snackbar.make(findViewById(R.id.layMain), result.message, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(User user) {

        Snackbar.make(findViewById(R.id.layMain), "Selamat datang", Snackbar.LENGTH_SHORT).show();
    }
}
