package open.app

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
// import androidx.fragment.app.FragmentPagerAdapter
// import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

import open.app.FragmentHome
import open.app.MainActivity

// class TabsStateAdapter : FragmentPagerAdapter {
    // private val context: Context? = null
    // private lateinit val context: Context

    // constructor(firstName: String) : this(firstName, "")
    /*
    constructor(fm: FragmentManager, lc: Lifecycle) : super(fm, lc) { }

    constructor(context: Context, fragment: Fragment) : super(fragment.childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        this.context = context
    }
    constructor(activity: FragmentActivity) : super(activity.supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        this.context = activity
    }
    private val context: Context
*/

// (fm: FragmentManager, ctx : Context, lc: Lifecycle) : FragmentStateAdapter(fm, ctx, lc)
//
    /*
    override fun getPageTitle(position: Int): CharSequence {
        val t = super.getPageTitle(position)
       Toast.makeText(context, t, Toast.LENGTH_LONG).show()
       return t
        // return tabs[position].title
    }  */

class CircularPagerAdapter
(fm: FragmentManager, lc: Lifecycle)  
// (fm: FragmentManager)  
// (fa : FragmentActivity)
: FragmentStateAdapter
(fm, lc)
// (fm)
// (fa)
{
    // val fm: FragmentManager = fm
    /*
    constructor(activity: FragmentActivity) : super(activity) {
        this.context = activity
    }
    constructor(fm: FragmentManager, lc : Lifecycle) : super(fm, lc) {
        this.context = null
    }
    */

    // private val context: Context?

    override fun getItemCount() = Integer.MAX_VALUE
    // override fun onActivityCreated() : Fragment {

    /**
     * Create the fragment based on the position
     */
    // override fun createFragment(position: Int) = HomePagerScreens.values()[position % HomePagerScreens.values().size].fragment.java.newInstance()
    // override fun createFragment(position: Int) = HomePagerScreens.values()[position % HomePagerScreens.values().size].fragment.createInstance()
    
    override fun createFragment(position: Int) : FragmentHome {
            Log.e("myTag", " create fragment  ")
        // this@MainActivity
        // obj.getContext()
        // fm.getContext
        // fm.getActivity
        
        var f:FragmentHome? = null
        // f = FragmentHome.newInstance(imageId, title, details1, details2)

        val pos = position % HomePagerScreens.values().size
        val obj = HomePagerScreens.values()[pos]
        val t = obj.title2
        // val f = obj.fragment
       // val i = FragmentHome.newInstance(t.toString())
       val i = FragmentHome.newInstance(Fragment.getString(t))
       // val i = FragmentHome.newInstance("gjhb")
        // val i = f.java.newInstance(t)
        // val i = f.newInstance(t)
        // var et :EditText= i.findViewById(R.id.et)
         // et.setText(t)
       Log.e("myTag",obj.toString())
        Log.e("myTag",f.toString())
        Log.e("myTag",i.toString())
        try {
        // val c = .getActivity()
       // val c = i.getActivity()
       // Toast.makeText(context, obj.toString(), Toast.LENGTH_LONG).show()

        // newInstance.getChildAt(0).setText(t)
        // val newInstance = MyDialogFragment.newInstance(extras)
        // val newInstance = MyDialogFragment().apply {
            // arguments = extras
        // }
        // newInstance.setText(newInstance.title)
        } catch (e : Exception) {
            Log.e("myTag", e.toString())
        // Toast.makeText(c, e.toString(), Toast.LENGTH_LONG).show()       
            }
        return i
    }

    /**
     * Returns the same id for the same Fragment.
     */
    override fun getItemId(position: Int): Long = (position % HomePagerScreens.values().size).toLong()

    // fun getCenterPage(position: Int = 0) = Integer.MAX_VALUE / 2 + position
    fun getCenterPage(position: Int = 0) : Int {
       return Integer.MAX_VALUE / 2 + position
    }
}