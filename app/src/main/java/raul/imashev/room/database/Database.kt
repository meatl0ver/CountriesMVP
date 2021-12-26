package raul.imashev.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import raul.imashev.room.dao.CountryDao
import raul.imashev.room.model.RoomCountry

@androidx.room.Database(
    entities = [RoomCountry::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val countryDao: CountryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}