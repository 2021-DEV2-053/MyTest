package mytest.tictactoe.data.source.mapper

import mytest.tictactoe.data.source.entity.GameEntity
import mytest.tictactoe.data.util.EntityMapper
import mytest.tictactoe.domain.model.Game
import javax.inject.Inject

class GameMapper @Inject constructor(
    private val playerMapper: PlayerMapper
): EntityMapper<GameEntity, Game> {

    override fun mapFromEntity(entity: GameEntity): Game {
        return Game(
            id = entity.id,
            createdAt = entity.createdAt,
            endedAt = entity.endedAt,
            status = entity.status,
            playerX = playerMapper.mapFromEntity(entity.playerx),
            playerO = playerMapper.mapFromEntity(entity.playero),
            currentPlayer = playerMapper.mapFromEntity(entity.currentPlayer),
            winningPlayer = playerMapper.mapFromEntity(entity.winningPlayer),
            losingPlayer = playerMapper.mapFromEntity(entity.losingPlayer)
        )
    }

    override fun mapToEntity(domainEntity: Game): GameEntity {
        return GameEntity(
            id = domainEntity.id,
            createdAt = domainEntity.createdAt,
            endedAt = domainEntity.endedAt,
            status = domainEntity.status,
            playerx = playerMapper.mapToEntity(domainEntity.playerX),
            playero = playerMapper.mapToEntity(domainEntity.playerO),
            currentPlayer = playerMapper.mapToEntity(domainEntity.currentPlayer),
            winningPlayer = playerMapper.mapToEntity(domainEntity.winningPlayer),
            losingPlayer = playerMapper.mapToEntity(domainEntity.losingPlayer)
        )
    }

    fun mapFromEntityList(entities: List<GameEntity>): List<Game>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(players: List<Game>): List<GameEntity>{
        return players.map { mapToEntity(it) }
    }

}