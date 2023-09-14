package luvnhi.luvbap.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import luvnhi.luvbap.R
import luvnhi.luvbap.base.BaseActivity
import luvnhi.luvbap.base.checkPermissionStorage
import luvnhi.luvbap.base.requestPermissionStorage
import luvnhi.luvbap.databinding.ActivityMainBinding
import luvnhi.luvbap.ui.adapter.HomeAdapter
import luvnhi.luvbap.utils.setStatusBarDarkOrLight

@SuppressLint("NewApi")
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var homeAdapter: HomeAdapter? = null
    private var listData: List<Any> = arrayListOf()

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        paddingStatusBar(binding.root)
        setStatusBarDarkOrLight()
        setupAdapter()
        if (!checkPermissionStorage) {
            requestPermissionStorage(1115)
        } else {
            viewModel.loadLocalStorage()
        }
    }


    private fun setupAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = homeAdapter
        homeAdapter?.submitList(listData)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1115) {
            if (grantResults.permissionGranted) {
                Log.i("onRequestPermissionsResult", "ALLOW")
            } else {
                Log.i("onRequestPermissionsResult", "DENIED")
            }
        }
    }

    val IntArray.permissionGranted: Boolean get() = !any { it != PackageManager.PERMISSION_GRANTED }


    override fun initListener() {
    }

    override fun observerLiveData() {
        viewModel.dataLocal.observe(this) {
            Log.i("observerLiveData", "${it.size}")
            it.forEach{ r ->
                Log.i("observerLiveData", "${r.name}")
            }
            homeAdapter?.submitList(it)
        }
    }
}