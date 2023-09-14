package luvnhi.luvbap.utils

import android.content.ContentUris
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import luvnhi.luvbap.MyApp
import luvnhi.luvbap.data.model.Ringtone

object FileUtils {

    fun localStorageQuery(): LiveData<List<Ringtone>> {
        var checkLimitation = 0
        val resultLiveData = MutableLiveData<List<Ringtone>>()
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MEDIA_TYPE
        )
        val selection = "${MediaStore.Files.FileColumns.MEDIA_TYPE} = " + "${MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO}"

        val order = "${MediaStore.Files.FileColumns.DATE_ADDED} DESC"
        val destination = MediaStore.Files.getContentUri("external")
        val cursor = MyApp.instance.contentResolver.query(
            destination,
            projection,
            selection,
            null,
            order
        )
        cursor?.use {
            val results = mutableListOf<Ringtone>()
            while (it.moveToNext() && checkLimitation++ <= 100) {
                results.add(Ringtone().apply {
                    val id = it.getLong(
                        it.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                    )
                    this.id = id.toString()
                    name = it.getString(
                        it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                    )
                    uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
                    ).toString()
                })
            }
            resultLiveData.value = results
        }
        return resultLiveData

    }

}