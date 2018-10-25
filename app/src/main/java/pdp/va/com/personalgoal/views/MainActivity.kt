package pdp.va.com.personalgoal.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pdp.va.com.personalgoal.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FilmsListFragment.newInstance())
                .commitNow()
        }
    }

    fun addFragment(fragment: Fragment){
        Log.i("Adding new fragment", "fragment")
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment::class.java.simpleName)
                .addToBackStack(fragment::class.java.simpleName)
                .commit()
    }

}
