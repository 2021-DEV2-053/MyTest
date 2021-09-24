package mytest.tictactoe.data.source.mapper

import mytest.tictactoe.data.source.entity.GameEntity
import mytest.tictactoe.data.util.EntityMapper
import mytest.tictactoe.domain.model.Game
import javax.inject.Inject

class GameMapper @Inject constructor(

): EntityMapper<GameEntity, Game> {

    override fun mapFromEntity(entity: GameEntity): Game {
        return Game(
            gameId = entity.gameId,
            createdAt = entity.createdAt,
            endedAt = entity.endedAt,
            playerX = entity.playerxId,
            playerO = entity.playeroId,
            winningPlayer = entity.winningPlayerId
        )
    }

    override fun mapToEntity(domainEntity: Game): GameEntity {
        return GameEntity(
            gameId = domainEntity.gameId,
            createdAt = domainEntity.createdAt,
            endedAt = domainEntity.endedAt,
            playerxId = domainEntity.playerX,
            playeroId = domainEntity.playerO,
            winningPlayerId = domainEntity.winningPlayer
        )
    }

    fun mapFromEntityList(entities: List<GameEntity>): List<Game>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(players: List<Game>): List<GameEntity>{
        return players.map { mapToEntity(it) }
    }

}