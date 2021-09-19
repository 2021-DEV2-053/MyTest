package mytest.tictactoe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mytest.tictactoe.data.source.AppDatabase

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }

}