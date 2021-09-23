package mytest.tictactoe.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mytest.tictactoe.data.repository.GamesRepositoryImpl
import mytest.tictactoe.data.repository.PlayersRepositoryImpl
import mytest.tictactoe.data.source.GamesLocalDataSource
import mytest.tictactoe.data.source.GamesLocalDataSourceImpl
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.GamesDao
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.mapper.GameMapper
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.repository.GamesRepository
import mytest.tictactoe.domain.repository.PlayersRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Tictactoe-db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePlayersDao(appDatabase: AppDatabase): PlayersDao {
        return appDatabase.playersDao()
    }

    @Provides
    fun provideGamesDao(appDatabase: AppDatabase): GamesDao {
        return appDatabase.gamesDao()
    }

    @Singleton
    @Provides
    fun providePlayersLocalDataSource(
        playerDao: PlayersDao,
        playerMapper: PlayerMapper
    ): PlayersLocalDataSource = PlayersLocalDataSourceImpl(playerDao, playerMapper)

    @Singleton
    @Provides
    fun provideGamesLocalDataSource(
        gamesDao: GamesDao,
        gameMapper: GameMapper
    ): GamesLocalDataSource = GamesLocalDataSourceImpl(gamesDao, gameMapper)

    @Singleton
    @Provides
    fun providePlayersRepository(
        playersLocalDataSource: PlayersLocalDataSource
    ): PlayersRepository = PlayersRepositoryImpl(playersLocalDataSource)

    @Singleton
    @Provides
    fun provideGamesRepository(
        gamesLocalDataSource: GamesLocalDataSource
    ): GamesRepository = GamesRepositoryImpl(gamesLocalDataSource)

}