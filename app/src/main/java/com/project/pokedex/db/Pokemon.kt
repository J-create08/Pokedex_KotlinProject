package com.project.pokedex.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon (@PrimaryKey  val id: String,
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "weight") val weight: String,
                    @ColumnInfo(name = "height") val height: String,
                    @ColumnInfo(name = "baseExp") val baseExp: String,
                    )