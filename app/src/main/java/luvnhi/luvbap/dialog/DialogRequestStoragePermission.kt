package luvnhi.luvbap.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.x5.pbn.base.BaseDialog
import luvnhi.luvbap.R
import luvnhi.luvbap.databinding.DialogRequestPermissionWriteStoragesBinding

class DialogRequestStoragePermission : BaseDialog<DialogRequestPermissionWriteStoragesBinding>() {

    private var onClickAccept : (()-> Unit) ?= null
    private var onClickDeny : (()-> Unit) ?= null

    fun setOnClickListener(onClickAccept : ()-> Unit, onClickDeny: () -> Unit){
        this.onClickAccept = onClickAccept
        this.onClickDeny = onClickDeny
    }
    override fun getGravityForDialog(): Int {
        return Gravity.BOTTOM
    }

    override fun getLayoutResource(): Int {
        return R.layout.dialog_request_permission_write_storages
    }

    override fun init(saveInstanceState: Bundle?, view: View?) {

    }

    override fun setUp(view: View?) {
        binding.btnCancel.setOnClickListener {
            onClickDeny?.invoke()
            dismiss()
        }
        binding.btnViewReward.setOnClickListener {
            onClickAccept?.invoke()
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onStart() {
        super.onStart()
        setSizeFullForDialog()
    }
}