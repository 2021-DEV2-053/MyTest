package mytest.tictactoe.data.source.mapper

import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.data.util.EntityMapper
import mytest.tictactoe.domain.model.Player
import javax.inject.Inject

class PlayerMapper @Inject constructor(
):EntityMapper<PlayerEntity, Player> {

    override fun mapFromEntity(entity: PlayerEntity): Player {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainEntity: Player): PlayerEntity {
        TODO("Not yet implemented")
    }

}