package id.chirikualii.base_clean_arch_example.utils.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun String?.toCurrency(): String{
    return try {
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','
        val decimalFormat = DecimalFormat("###,###,###,###",symbols)

        val formatCurrency = decimalFormat.format(this?.toInt())

        "Rp. ${formatCurrency.replace(',','.')}"
    }catch (e:Exception){
        "Rp. $this"
    }




}
