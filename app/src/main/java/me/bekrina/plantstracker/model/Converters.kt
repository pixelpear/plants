package me.bekrina.plantstracker.model

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun fromString(value: String): OffsetDateTime? {
        var date: OffsetDateTime? = null
        if (value != "") {
            date = OffsetDateTime.parse(value, formatter)
        }
        return date
    }

    @TypeConverter
    fun fromOffsetDateTime(date: OffsetDateTime?): String {
        var formattedDate = ""
        if (date != null) {
            formattedDate = date.format(formatter)
        }
        return formattedDate
    }
}