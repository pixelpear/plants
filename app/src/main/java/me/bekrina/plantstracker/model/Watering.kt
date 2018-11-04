package me.bekrina.plantstracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

class Watering(val date: OffsetDateTime) {
    @PrimaryKey(autoGenerate = true)
    private var uid: Int = 0

    @ColumnInfo(name = "volume", typeAffinity = ColumnInfo.INTEGER)
    private var volume = 1

    @ForeignKey(entity = Watering::class, parentColumns = ["uid", "name", "type", "watering_period"],
        childColumns = ["uid", "volume", "plant_id"])
    private lateinit var plant: Plant

}