package com.example.movieapp.utils

import android.app.Activity
import android.app.AlertDialog
import com.example.movieapp.R

class CargandoDialog(private val mActivity: Activity) {
    private lateinit var isDialog: AlertDialog
    fun startLoading() {
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.cargando_item, null)
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.window?.decorView?.setBackgroundResource(R.drawable.dialog_fondo)
        isDialog.show()
    }

    fun isDismiss() {
        isDialog.dismiss()
    }
}