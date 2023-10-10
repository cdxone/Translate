package com.google.mlkit.samples.nl.translate.utils

import android.util.Log
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.samples.nl.translate.cons.TAG
import com.google.mlkit.samples.nl.translate.cons.TransState
import com.google.mlkit.samples.nl.translate.modal.LanguageN

class TranslateUtils {

    companion object {

        fun translate(sourceText: String, sourceLang: LanguageN, targetLang: LanguageN, function: (String) -> Unit) {
            // 下载语言包
            downloadModal(sourceLang, targetLang) { translator, transState ->
                if (transState == TransState.StateSuccess) {
                    translator.translate(sourceText)
                        .addOnSuccessListener {
                            function(it)
                            Log.i(TAG.TAG_TRANS, "翻译成功:$it")
                        }
                        .addOnFailureListener {
                            Log.i(TAG.TAG_TRANS, "翻译失败:$it")
                        }
                } else if (transState == TransState.StateFail) {

                }
            }
        }

        /**
         * 下载modal
         */
        private fun downloadModal(
            sourceLang: LanguageN, targetLang: LanguageN, function: (Translator, TransState) -> Unit
        ) {
            // 获取语言码
            val sourceLangCode = TranslateLanguage.fromLanguageTag(sourceLang.code)!!
            val targetLangCode = TranslateLanguage.fromLanguageTag(targetLang.code)!!

            // 构建选项（源语言和目标语言）
            val options = TranslatorOptions.Builder().setSourceLanguage(sourceLangCode).setTargetLanguage(targetLangCode).build()

            // 下载
            val client = Translation.getClient(options)
            client.downloadModelIfNeeded().addOnSuccessListener {
                Log.i(TAG.TAG_TRANS, "下载语言包成功")
                function.invoke(client, TransState.StateSuccess)
            }.addOnFailureListener { exception ->
                Log.i(TAG.TAG_TRANS, "下载语言包失败")
                function.invoke(client, TransState.StateFail)
            }
        }
    }

}