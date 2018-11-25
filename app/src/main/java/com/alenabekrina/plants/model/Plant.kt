package com.alenabekrina.plants.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "plants")
class Plant {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int = 0

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    lateinit var name: String

    @ColumnInfo(name = "type", typeAffinity = ColumnInfo.TEXT)
    lateinit var type: String

    @ColumnInfo(name = "watering_period", typeAffinity = ColumnInfo.INTEGER)
    var wateringInterval: Int = 0

    @ColumnInfo(name = "days_since_last_watering", typeAffinity = ColumnInfo.INTEGER)
    var daysSinceLastWatering: Int = 0
}
