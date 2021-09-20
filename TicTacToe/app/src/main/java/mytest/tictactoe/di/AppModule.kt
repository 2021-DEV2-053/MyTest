package mytest.tictactoe.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.mapper.PlayerMapper

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    fun providePlayersLocalDataSource(
        playerDao: PlayersDao,
        playerMapper: PlayerMapper
    ): PlayersLocalDataSource = PlayersLocalDataSourceImpl(playerDao, playerMapper)

}