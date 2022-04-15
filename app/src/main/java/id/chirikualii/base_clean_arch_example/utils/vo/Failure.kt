package id.chirikualii.base_clean_arch_example.utils.vo

data class Failure(val throwable: Throwable,val message:String = throwable.message.toString())