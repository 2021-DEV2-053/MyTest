package mytest.tictactoe

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.model.Player
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.After
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PlayerEntityTest {

    private lateinit var db: AppDatabase
    private lateinit var playersDao: PlayersDao
    private lateinit var playerMapper: PlayerMapper
    private lateinit var playersLocalDataSource: PlayersLocalDataSource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java).build()
        playersDao = db.playersDao()
        playerMapper = PlayerMapper()
        playersLocalDataSource = PlayersLocalDataSourceImpl(playersDao, playerMapper)

    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun playerMapperTest() {

        val playerDomain = Player(id=1, name="george")

        playersDao.insertAll(playerMapper.mapToEntity(playerDomain))
        val playerEntity = playersDao.findAllByName("george").get(0)

        assertThat(playerMapper.mapFromEntity(playerEntity), equalTo(playerDomain))
    }

    @Test
    @Throws(Exception::class)
    fun getPlayersFromLocalDataSourceTest() {

        val player1Entity = PlayerEntity(id=1, name="george")
        val player2Entity = PlayerEntity(id=2, name="John")
        val player3Entity = PlayerEntity(id=3, name="Smith")

        playersDao.insertAll(player1Entity, player2Entity, player3Entity)

        
    }

}