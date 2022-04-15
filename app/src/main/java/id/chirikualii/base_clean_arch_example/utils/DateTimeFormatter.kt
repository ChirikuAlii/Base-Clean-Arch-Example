package id.chirikualii.base_clean_arch_example.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {

    fun toDate(dateInput :String,dateFormat: String = "MM/dd/yy HH.mm", dateFormatFromInput:String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String {
        try {
            val cal = Calendar.getInstance()
            val tz: TimeZone = cal.getTimeZone()
            val parser = SimpleDateFormat(dateFormatFromInput)
            parser.timeZone =TimeZone.getTimeZone("UTC")
            val date = parser.parse(dateInput)
            val newDate =SimpleDateFormat(dateFormat,Locale.ENGLISH)
            return  "${newDate.format(date)}"
        }catch (e:Exception){
            Log.e(DateTimeFormatter.javaClass.simpleName, "error change format date", )
        }

        return dateInput
    }
}