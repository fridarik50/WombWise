package hackeru.fridarik.wombwise

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hackeru.fridarik.wombwise.entity.PregnantWoman
import hackeru.fridarik.wombwise.services.PregnantWomanDao


@Database(entities = [PregnantWoman::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pregnantWomanDao() : PregnantWomanDao

    companion object {
        private var instance : AppDatabase? = null

        fun getInstance(context:Application): AppDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "fridarik_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

    }
}