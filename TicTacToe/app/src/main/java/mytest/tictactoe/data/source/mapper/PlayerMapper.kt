package mytest.tictactoe.data.source.mapper

import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.data.util.EntityMapper
import mytest.tictactoe.domain.model.Player
import javax.inject.Inject

class PlayerMapper @Inject constructor(

): EntityMapper<PlayerEntity?, Player?> {

    override fun mapFromEntity(entity: PlayerEntity?): Player? {
        if(entity == null){
            return null
        }
        return Player(
            id = entity.playerId,
            name = entity.name
        )
    }

    override fun mapToEntity(domainEntity: Player?): PlayerEntity? {
        if(domainEntity == null){
            return null
        }
        return PlayerEntity(
            playerId = domainEntity.id,
            name = domainEntity.name
        )
    }

    fun mapFromEntityList(entities: List<PlayerEntity>): List<Player>{
        return entities.map { mapFromEntity(it)!! }
    }

    fun mapToEntityList(players: List<Player>): List<PlayerEntity>{
        return players.map { mapToEntity(it)!! }
    }

}