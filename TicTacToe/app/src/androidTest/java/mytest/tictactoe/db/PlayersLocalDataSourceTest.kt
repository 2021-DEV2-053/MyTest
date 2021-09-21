package mytest.tictactoe.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.model.Player
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayersLocalDataSourceTest {

    @get:Rule
    val instanTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var playersDao: PlayersDao
    private lateinit var playerMapper: PlayerMapper

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        playersDao = db.playersDao()
        playerMapper = PlayerMapper()
    }

    @Test
    @Throws(Exception::class)
    fun `mapFromAndToEntityTest`() {
        val playerDomain = Player( name = "george")

        //mapToEntity
        val playerEntity = playerMapper.mapToEntity(playerDomain)
        // insert player
        playersDao.insertAll(playerEntity)
        //get player from Entity
        val playerFromEntity = playersDao.findPlayerByName("george")
        //mapFromEntity
        val player= playerMapper.mapFromEntity(playerFromEntity)

        assertEquals(1, playersDao.getAll().size)
        assertEquals(playerDomain.name, playerFromEntity.name)
        assertEquals(playerDomain.name, player.name)
    }

    @Test
    @Throws(Exception::class)
    fun `insertTwoPlayersDistinctTest`() {

        val player1Entity = PlayerEntity( name = "george")
        val player2Entity = PlayerEntity( name = "john")

        // insert players
        playersDao.insertAll(player1Entity, player2Entity)

        //get players
        val player1 = playersDao.findPlayerByName("george")
        val player2 = playersDao.findPlayerByName("john")

        assertEquals(2, playersDao.getAll().size)
        assertEquals("george", player1.name)
        assertEquals("john", player2.name)
    }

    @Test
    @Throws(Exception::class)
    fun `insertTwoPlayersWithSameNameTest`() {

        val player1Entity = PlayerEntity( name = "john")
        val player2Entity = PlayerEntity( name = "john")

        // insert players
        playersDao.insertAll(player1Entity, player2Entity)

        //get player
        val player = playersDao.findPlayerByName("john")

        assertEquals(1, playersDao.getAll().size)
        assertEquals("john", player.name)
    }

    @After
    fun closeDb() {
        db.close()
    }
}