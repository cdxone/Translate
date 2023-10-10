package com.google.mlkit.samples.nl.translate

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.samples.nl.translate.modal.LanguageN
import com.google.mlkit.samples.nl.translate.utils.ModelUtils
import com.google.mlkit.samples.nl.translate.utils.TranslateUtils

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btn1).setOnClickListener {
            // 开始翻译
            TranslateUtils.translate("Hello", LanguageN("en"), LanguageN("zh")) {

            }
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            // 得到存储的语言
            ModelUtils.getAllModel { state, list ->

            }
        }

        findViewById<Button>(R.id.btn3).setOnClickListener {
            // 删除模型
            ModelUtils.deleteModel(TranslateLanguage.CHINESE) {

            }
        }

        findViewById<Button>(R.id.btn4).setOnClickListener {
            // 下载模型
            ModelUtils.downloadModel(TranslateLanguage.CHINESE) {

            }
        }
    }

}