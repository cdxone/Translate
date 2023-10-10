package com.google.mlkit.samples.nl.translate.utils

import android.util.Log
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.samples.nl.translate.cons.ModelState
import com.google.mlkit.samples.nl.translate.cons.TAG

class ModelUtils {

    companion object {

        val modelManager = RemoteModelManager.getInstance()

        fun getAllModel(function: (ModelState, List<String>) -> Unit) {
            modelManager.getDownloadedModels(TranslateRemoteModel::class.java)
                .addOnSuccessListener { models ->
                    val list = models.sortedBy { it.language }.map { it.language }
                    function(ModelState.StateSuccess, list)
                    Log.i(TAG.TAG_TRANS, "存儲的模型为：$list")
                }
                .addOnFailureListener {
                    // Error.
                    Log.e(TAG.TAG_TRANS, "存儲的模型失败：$it")
                    function(ModelState.StateFail, listOf())
                }
        }

        /**
         * 删除模型
         */
        fun deleteModel(code: String, function: (ModelState) -> Unit) {
            val germanModel = TranslateRemoteModel.Builder(code).build()
            modelManager.deleteDownloadedModel(germanModel)
                .addOnSuccessListener {
                    function(ModelState.StateSuccess)
                    Log.i(TAG.TAG_TRANS, "删除模型成功")
                }
                .addOnFailureListener {
                    function(ModelState.StateFail)
                    Log.i(TAG.TAG_TRANS, "删除模型失败")
                }
        }

        /**
         * 下载模型
         */
        fun downloadModel(code: String, function: (ModelState) -> Unit) {
            val frenchModel = TranslateRemoteModel.Builder(code).build()
            val conditions = DownloadConditions.Builder()
                .requireWifi()
                .build()
            modelManager.download(frenchModel, conditions)
                .addOnSuccessListener {
                    function(ModelState.StateSuccess)
                    Log.i(TAG.TAG_TRANS, "下载语言模型成功")
                }
                .addOnFailureListener {
                    function(ModelState.StateFail)
                    Log.i(TAG.TAG_TRANS, "下载语言模型失败")
                }
        }

    }

}