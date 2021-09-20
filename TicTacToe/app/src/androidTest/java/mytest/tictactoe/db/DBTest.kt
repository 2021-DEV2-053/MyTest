package mytest.tictactoe.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.entity.PlayerEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBTest {
    @get:Rule
    val instanTaskExecutorRule = InstantTaskExecutorRule()

    private var playersDao: PlayersDao? = null
    private var db: AppDatabase? = null

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        playersDao = db?.playersDao()
    }

    @Test
    @Throws(Exception::class)
    fun `inserAndGetDataFromDBRoom`() {
        val playerEntity = PlayerEntity(id = 1, name = "george")

        // insert
        playersDao?.insertAll(playerEntity)
        //get player
        val playerInserted = playersDao?.findAllByName("george")?.get(0)

        assertThat(playerEntity, equalTo(playerInserted))
    }

    @After
    fun closeDb() {
        db?.close()
    }
}