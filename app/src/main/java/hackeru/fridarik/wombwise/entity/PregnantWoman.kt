package hackeru.fridarik.wombwise.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "pregnant_woman")
data class PregnantWoman(
    @PrimaryKey val id: Long = 0,
    var name: String,
    var age: Int,
    var lastPeriod: Long
)
