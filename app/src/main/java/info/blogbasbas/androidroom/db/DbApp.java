package info.blogbasbas.androidroom.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import info.blogbasbas.androidroom.db.dao.DaoPeserta;
import info.blogbasbas.androidroom.db.model.ModelPeserta;

/**
 * Created by User on 09/03/2018.
 */
@Database(entities = {ModelPeserta.class}, version = 1)
public abstract class DbApp extends RoomDatabase {

    private static volatile DbApp INSTANCE;

    public static DbApp getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DbApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DbApp.class, "user_db").build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract DaoPeserta itemAndPesertaModel();
}
