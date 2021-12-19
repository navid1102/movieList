package com.navid.movielist_mvvm.ui.splash

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.navid.movielist_mvvm.R
import com.navid.movielist_mvvm.databinding.ActivitySplashBinding
import com.navid.movielist_mvvm.module.BaseActivity
import com.navid.movielist_mvvm.module.Util
import com.navid.movielist_mvvm.ui.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    val vm: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun getViewRes(): Int {
        return R.layout.activity_splash
    }


    override fun oncreat() {
        binding.splashViewModel = vm
    }

    //todo we can have offline mode
    //******** for offline mode we can save data in db or SharedPreferences ********
    // inject ms here and ... -> ex :  ms.movieList for online mode
    // now i just show offline view
    val broadcastReceiver: BroadcastReceiver =
        object : BroadcastReceiver() {
            override fun onReceive(
                context: Context,
                intent: Intent
            ) {
                try {
                    if (Util.isOnline(context)) {
                        binding.imgNoConnection.visibility = View.GONE
                        binding.txtNoConnection.visibility = View.GONE
                        showActivity(MainActivity::class.java)
                    } else {
                        binding.imgNoConnection.visibility = View.VISIBLE
                        binding.txtNoConnection.visibility = View.VISIBLE

                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
            }
        }

    fun showActivity(c: Class<*>?) {
        val intent = Intent(this@SplashActivity, c)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        applicationContext.registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

    }

    override fun onStop() {
        super.onStop()
        applicationContext.unregisterReceiver(broadcastReceiver)
    }
}