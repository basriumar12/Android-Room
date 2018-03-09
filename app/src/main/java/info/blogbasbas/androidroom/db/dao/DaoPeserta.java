package info.blogbasbas.androidroom.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import info.blogbasbas.androidroom.db.model.ModelPeserta;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by User on 09/03/2018.
 */
@Dao
public interface DaoPeserta {
    @Query("select * from peserta")
    LiveData<List<ModelPeserta>> getAllPeserta();

    @Query("select * from peserta where id_peserta = :id")
    ModelPeserta getPeserta(String id);

    @Insert(onConflict = REPLACE)
    void addPeserta(ModelPeserta modelPeserta);

    @Delete()
    void deletePeserta(ModelPeserta modelPeserta);
}