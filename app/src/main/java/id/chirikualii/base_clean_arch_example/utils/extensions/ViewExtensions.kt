package id.chirikualii.base_clean_arch_example.utils.extensions

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.util.Log.e
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

val Int.dp : Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun ImageView.glide(view: View, image: Any) {
    try { Glide.with(view)
        .load(image)
        .into(this)
    } catch (ignored: Throwable) { }
}

fun ImageView.glide(activity: Activity, image: Any) {
    try { Glide.with(activity)
        .load(image)
        .into(this)
    } catch (ignored: Throwable) { }
}

fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun TextView.toHtml(bodyData:String){
    if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(bodyData,Html.FROM_HTML_MODE_LEGACY);
    } else {
        this.text = Html.fromHtml(bodyData);
    }
}

fun String.toDate(dateFormat: String = "MM/dd/yy HH.mm",dateFormatFromServer:String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String {
    try {
        val cal = Calendar.getInstance()
        val tz: TimeZone = cal.getTimeZone()
        val parser = SimpleDateFormat(dateFormatFromServer)
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val date = parser.parse(this)
        val newDate = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        return  "${newDate.format(date)}"
    }catch (e: Exception){
        e("ViewExtensions", "error change format date ", )
    }

    return this
}

//fun String.toDateTime(dateFormat:String ="yyyy-MM-dd"):DateTime{
//    val dateTimeFormatter = DateTimeFormat.forPattern(dateFormat)
//    val dateTime = dateTimeFormatter.parseDateTime(this)
//    return dateTime
//}

fun String.dateTotimeMillis(format: String?=null):Long{
    var format = format
    if(format == null){
        format = "MM/dd/yy\nHH.mm"
    }

    val dateFormat= SimpleDateFormat(format)

    return dateFormat.parse(this).time
}