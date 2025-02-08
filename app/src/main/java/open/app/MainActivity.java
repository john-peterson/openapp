
package open.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import open.app.databinding.ActivityMainBinding;
import open.app.KeyInterceptor;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	public boolean onKeyDown(int key, KeyEvent event) {
		// eMappedToEscape() || keyCode != KeyEvent.KEYCODE_BACK))
		// Toast.makeText(this, "down "+ key, Toast.LENGTH_SHORT).show();
		// KeyInterceptor.isCap(this);
		return super.onKeyDown(key, event);
	}
/*
	@Override
    public boolean onKeyEvent(KeyEvent event) {
		Toast.makeText(this, "event key "+ event.getKeyCode() + " ",Toast.LENGTH_SHORT).show();
		return super.onKeyEvent(event);
		}
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate and get instance of binding
		binding = ActivityMainBinding.inflate(getLayoutInflater());

		// set content view to binding's root
		setContentView(binding.getRoot());
        KeyInterceptor.setContext(this);
		// Toast.makeText(this, " launched= " + KeyInterceptor.isLaunched(), Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
