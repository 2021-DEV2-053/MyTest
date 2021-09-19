package mytest.tictactoe.data.source

import android.content.Context
import io.realm.BuildConfig
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog

class AppDatabase {

    companion object{
        private const val databaseName = "tictactoe-realm"

        fun init(ctx: Context){
            Realm.init(ctx)

            val config = RealmConfiguration.Builder()
                .name(databaseName)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build()
            // set this config as the default realm
            Realm.setDefaultConfiguration(config)

            if(BuildConfig.DEBUG){
                RealmLog.setLevel(LogLevel.ALL)
            }
        }
    }
}