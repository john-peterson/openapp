
package open.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import open.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	public boolean onKeyDown(int key, KeyEvent event) {
		// eMappedToEscape() || keyCode != KeyEvent.KEYCODE_BACK))
		Toast.makeText(this, "roast"+ key + " ",Toast.LENGTH_SHORT).show();
		return super.onKeyDown(key, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate and get instance of binding
		binding = ActivityMainBinding.inflate(getLayoutInflater());

		// set content view to binding's root
		setContentView(binding.getRoot());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
