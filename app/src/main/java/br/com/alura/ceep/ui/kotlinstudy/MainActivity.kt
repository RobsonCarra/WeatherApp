package br.com.alura.ceep.ui.kotlinstudy

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.ui.kotlinstudy.domain.commands.RequestForecastCommand
import org.jetbrains.anko.*
import java.lang.reflect.Array.get
import java.util.*
import java.util.concurrent.Executors
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {
    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(this, message, duration).show()
        }

        toast("Hello world!")
//        toast("Hello world!", Toast.LENGTH_SHORT)
//        toast("Hello world!")
        longToast(R.string.hello_world)

        //        val  ViewGroup.childViews: List<View>
//         get() = (0 until childCount).map { getChildAt(it) }
//    }
//    val url = "https://github.com/antoniolg/Kotlin-for-Android-Developers.git"
        doAsync() {
            val result = RequestForecastCommand("94043").execute()
            uiThread { forecastList.adapter = ForecastListAdapter(result) }
        }
        data class Forecast(val date: Date, val temperature: Float, val details: String)

        val f1 = Forecast(Date(), 27.5f, "Shiny day")
        val f2 = f1.copy(temperature = 30f)
        var (date, temperature, details) = f1
        date = f1.component1()
        temperature = f1.component2()
        details = f1.component3()
        for ((key, value) in map) {
            Log.d("map", "key:$key, value:$value")
        }

    }
}