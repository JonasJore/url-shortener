package model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test")
data class TestEntity(
    @Id
    @GeneratedValue
    private val id: Long,
    @Column(unique = true)
    private val name: String
)