package me.bekrina.plantstracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "plants")
class Plant {
    @PrimaryKey(autoGenerate = true)
    private var uid: Int = 0

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private lateinit var name: String

    @ColumnInfo(name = "type", typeAffinity = ColumnInfo.TEXT)
    private lateinit var type: String

    @ColumnInfo(name = "watering_period", typeAffinity = ColumnInfo.INTEGER)
    private var wateringPeriod: Int = 0
}