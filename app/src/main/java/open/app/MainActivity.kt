package open.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import open.app.databinding.ActivityMainBinding

public class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
      get() = checkNotNull(_binding) { "Activity has been destroyed" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val circularAdapter = CircularPagerAdapter(supportFragmentManager, lifecycle)
        binding.vwpHome.apply {
            adapter = circularAdapter
            setCurrentItem(circularAdapter.getCenterPage(), false)
        }
    }
}
