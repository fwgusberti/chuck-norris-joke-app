package br.com.nerdrapido.chucknorrisjokeapp.joke

import android.content.Intent
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.base.BaseActivity
import br.com.nerdrapido.chucknorrisjokeapp.helper.ViewHelper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_joke.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokeActivity : BaseActivity<JokeViewModel>() {

    override val layoutId: Int = R.layout.activity_joke

    override val viewModel: JokeViewModel by viewModel()


    private lateinit var reloadingMessage: String

    private lateinit var errorSnackBar: Snackbar

    private lateinit var snackBarReload: Snackbar

    private lateinit var adapter: JokeFragmentPagerAdapter

    override fun onCreateCall() {
        super.onCreateCall()
        reloadingMessage = getString(R.string.string_trying_again)
        errorSnackBar = getErrorSnack()
        snackBarReload = getReloadSnack()
        jokePb.hide()
        adapter = JokeFragmentPagerAdapter(this)
        jokeFragmentContainerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        jokeFragmentContainerVp.isUserInputEnabled = true
        jokeFragmentContainerVp.adapter = adapter
        ViewHelper.loadImageUrlIntoView(getString(R.string.string_url_icon), icoIv)
        initializeShareButton()
    }

    override fun registerObservers() {
        super.registerObservers()
        viewModel.isLoading.observe(this, getIsLoadingObserver())
        viewModel.isApiError.observe(this, getIsErrorObserver(R.string.string_api_error_message))
        viewModel.isUnknownError.observe(this, getIsErrorObserver(R.string.string_error_message))
    }

    override fun getIsLoadingObserver(): Observer<Boolean> {
        return Observer {
            if (it) {
                jokePb.show()
            } else {
                jokePb.hide()
            }
        }
    }

    private fun getIsErrorObserver(@StringRes message: Int): Observer<Boolean> {
        return Observer {
            if (it) {
                showErrorSnack(message)
            } else {
                errorSnackBar.dismiss()
            }
        }
    }

    private fun showErrorSnack(@StringRes message: Int) {
        Timber.w("showErrorSnack")
        errorSnackBar.setText(getString(message))
        snackBarReload.dismiss()
        errorSnackBar.show()
    }

    private fun getErrorSnack() = Snackbar
        .make(
            coordinatorLayout as CoordinatorLayout,
            getString(R.string.string_error_message),
            Snackbar.LENGTH_INDEFINITE
        )
        .setAction(getString(R.string.string_try_again)) {
            Timber.d("try again")
            snackBarReload.show()
            viewModel.onNewJokeNeeded()
        }

    private fun getReloadSnack() = Snackbar.make(
        coordinatorLayout as CoordinatorLayout,
        reloadingMessage,
        Snackbar.LENGTH_SHORT
    )

    private fun initializeShareButton() {
        shareIv.setOnClickListener {
            val joke = viewModel.randomJokeListLiveData.value?.get(jokeFragmentContainerVp.currentItem)
                ?: return@setOnClickListener
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.string_share_subject))
            sharingIntent.putExtra(Intent.EXTRA_TEXT, joke.value)
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.string_share)))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
}
