package raul.imashev.ui.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import raul.imashev.App
import raul.imashev.R
import raul.imashev.databinding.ActivityMainBinding
import raul.imashev.ui.base.BackButtonListener
import raul.imashev.ui.screens.AndroidScreens

class MainActivity : MvpAppCompatActivity(), MainView {


    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
    private val navigator = AppNavigator(this, R.id.container)
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}