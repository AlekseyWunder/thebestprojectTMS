package com.example.egida

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.egida.databinding.LoginActivityBinding
import com.example.egida.presentation.ui.SingInFragment
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SingInFragment.newInstance())
                .commitNow()
        }
        val ids = listOf("abc", "def", "dsfs", "kia")
        val random = Random()
        val list: MutableList<Operation> = mutableListOf()
        for (i in 0..20) {
            list.add(Operation(ids[random.nextInt(ids.size)], random.nextInt(20) + 1))
        }
        val filtredList = list
            .sortedByDescending {
                it.summa
            }
            .sortedBy {
                it.id
            }
            .log()
            .groupBy {
                it.id
            }
            .map { entry ->
                val summa = entry.value
                    .map { operation ->
                        operation.summa
                    }
                    .fold(1000) { acc, element ->
                        acc + element
                    }
                Pair(entry.key, summa)
            }
            .log()
    }
//
//    val summa = filtredList.reduce { acc, element ->
//        acc.summa += element.summa
//        acc
//        }
//    Log.d("test", summa.toString())


}


fun <T> List<T>.log(): List<T> {
    this.forEach {
        Log.d("test", it.toString())
    }
    return this
}

data class Operation(
    val id: String,
    var summa: Int
)