package hackeru.fridarik.wombwise.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hackeru.fridarik.wombwise.AppDatabase
import hackeru.fridarik.wombwise.entity.PregnantWoman
import hackeru.fridarik.wombwise.services.PregnantWomanDao
import kotlinx.coroutines.launch

class UserDetailsViewModel : ViewModel() {

    lateinit var pregnantWomanLiveData: LiveData<PregnantWoman>
    private lateinit var dao: PregnantWomanDao
    fun initDB(context:Application) {
        dao = AppDatabase.getInstance(context).pregnantWomanDao()
        pregnantWomanLiveData = dao.listenPregnantWoman()
    }

    fun insertPregnantWoman(woman : PregnantWoman) {
        viewModelScope.launch {
            dao.insert(woman)
        }
    }

    fun deletePregnantWoman(woman : PregnantWoman) {
        viewModelScope.launch {
            dao.delete(woman)
        }
    }

    fun updatePregnantWoman(woman : PregnantWoman) {
        viewModelScope.launch {
            dao.update(woman)
        }
    }

}