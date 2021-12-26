package raul.imashev.ui.main

import raul.imashev.ui.screens.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens): MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.countriesList())
    }

    fun backClicked() {
        router.exit()
    }
}
