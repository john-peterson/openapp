package open.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import open.app.databinding.ActivityMainBinding

// import com.itsaky.androidide.logsender.LogSender

public class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
      get() = checkNotNull(_binding) { "Activity has been destroyed" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val circularAdapter = CircularPagerAdapter(supportFragmentManager, lifecycle)
        // val circularAdapter = CircularPagerAdapter(supportFragmentManager)
        // val circularAdapter = CircularPagerAdapter(supportFragmentActivity)
        // val circularAdapter = CircularPagerAdapter(supportFragmentManager, lc)
        // val circularAdapter = CircularPagerAdapter(sfm, lc)
        binding.vwpHome.apply {
            adapter = circularAdapter
            setCurrentItem(circularAdapter.getCenterPage(), false)
        }
    }
}
