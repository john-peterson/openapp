package open.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

import open.app.MainActivity

class FragmentHome : Fragment() {

		var data: String? = null

		companion object {
			fun newInstance(arg: String): FragmentHome {
				var bundle = Bundle()
				bundle.putString("arg", arg)
				var f = FragmentHome()
				f.arguments = bundle
				return f
			}
		}

		override fun onCreate(savedInstanceState: Bundle?) {
			super.onCreate(savedInstanceState)
			data = arguments?.getString("arg")
		}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            Log.e("myTag", "on create view ")
        // return inflater.inflate(R.layout.fragment_home, container, false)
        // val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lo_customers, parent, false);
        // val v = inflater.inflate(R.layout.fragment_home, container, true)
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        // Toast.makeText(this@MainActivity, v.toString() ,Toast.LENGTH_SHORT).show()
        // Toast.makeText(MainActivity.this, v.toString() ,Toast.LENGTH_SHORT).show()
        var et :EditText= v.findViewById(R.id.et)
			// val tv = TextView(container?.context)
        try {
			// val et = EditText(container?.context)
			// et.text = data
         et.setText(data)
			// return et
        // val msg = v.getChildCount()
        // val msg = v.toString()
        // val msg = container!!.isNull()
        // val msg = container.isNullOrBlank()

        // val msg = container.toString() 
        // val msg = container!!.getChildCount()
        // Toast.makeText(v.context, msg ,Toast.LENGTH_LONG).show()
        // var et = v.getChildAt(0)
         // var txt = v.id
         // var txt = v.title
         // var txt = v.layout.getChildAt(0).title
         // et.setText(msg)
        // Toast.makeText(c, v.getChildCount() ,Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            Log.e("myTag", e.toString())
// val a =   e.message.isNullOrBlank().toString()
// val a =  e.message
         // et.setText("" + e.toString())
        // Toast.makeText(v.context, a, Toast.LENGTH_SHORT).show()
        // Toast.makeText(v.context,  e.toString(), Toast.LENGTH_LONG).show()
            }
         return v
    }
}