package luvnhi.luvbap.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import luvnhi.luvbap.data.model.Ringtone
import luvnhi.luvbap.repository.SystemRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SystemRepository
) : ViewModel() {

    private val dataLocalResult = MediatorLiveData<List<Ringtone>>()
    val dataLocal: LiveData<List<Ringtone>> = dataLocalResult

    fun loadLocalStorage() {
        val request = repository.getDataLocal()
        dataLocalResult.addSource(request) { listWallpaper ->
            dataLocalResult.postValue(listWallpaper)
        }
    }
}