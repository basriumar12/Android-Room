package info.blogbasbas.androidroom.ui.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import info.blogbasbas.androidroom.db.DbApp;
import info.blogbasbas.androidroom.db.model.ModelPeserta;

/**
 * Created by User on 09/03/2018.
 */

public class AddViewModel extends AndroidViewModel {
    //TODO panggil DBAPP
    private DbApp db;

    public AddViewModel(@NonNull Application application) {
        super(application);
        //TODO panggil database
        db = DbApp.getDatabase(this.getApplication());
    }

    public void addPeserta(ModelPeserta modelPeserta) {
        new addAsyncTask(db).execute(modelPeserta);
    }

    private class addAsyncTask extends AsyncTask<ModelPeserta, Void, Void> {
        private DbApp db;

        public addAsyncTask(DbApp db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(ModelPeserta... userModels) {
            db.itemAndPesertaModel().addPeserta(userModels[0]);
            return null;
        }
    }
}
