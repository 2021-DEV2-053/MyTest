package mytest.tictactoe.data.util

interface EntityMapper <Entity, DomainEntity>{
    fun mapFromEntity(entity: Entity): DomainEntity
    fun mapToEntity(domainEntity: DomainEntity): Entity
}