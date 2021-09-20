package mytest.tictactoe

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.entity.PlayerEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.After
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PlayerEntityTest {

    private lateinit var playersDao: PlayersDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java).build()
        playersDao = db.playersDao()

    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun writePlayerAndReadInList() {
        val player = PlayerEntity(id=1, name="george")
        playersDao.insertAll(player)
        val byName = playersDao.findAllByName("george")
        assertThat(byName.get(0), equalTo(player))
    }


}