package hackeru.fridarik.wombwise.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import hackeru.fridarik.wombwise.entity.PregnantWoman

@Dao
interface PregnantWomanDao {

    @Query("SELECT * from pregnant_woman LIMIT 1")
    suspend fun getPregnantWoman() : List<PregnantWoman>

    @Query("SELECT * from pregnant_woman LIMIT 1")
    fun listenPregnantWoman() : LiveData<PregnantWoman>

    @Insert(onConflict = REPLACE)
    suspend fun insert(woman : PregnantWoman)

    @Update
    suspend fun update(woman : PregnantWoman)

    @Delete
    suspend fun delete(woman : PregnantWoman)
}