package me.bekrina.plantstracker.model

import android.arch.persistence.room.*
import android.support.annotation.NonNull
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "waterings", foreignKeys = [ForeignKey(entity = Plant::class,
                                                        parentColumns = ["id"],
                                                        childColumns = ["plant_id"],
                                                        onDelete = ForeignKey.CASCADE)]
)
class Watering(val date: OffsetDateTime) {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid", typeAffinity = ColumnInfo.INTEGER)
    var uid: Int = 0

    @ColumnInfo(name = "volume", typeAffinity = ColumnInfo.INTEGER)
    var volume = 1

    @ColumnInfo(name = "dateTime", typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(Converters::class)
    var dateTime: OffsetDateTime = OffsetDateTime.MIN

    @ColumnInfo(name = "plant_id")
    var plant_id: Int = 0

}
