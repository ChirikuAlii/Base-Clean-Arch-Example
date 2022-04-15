package id.chirikualii.base_clean_arch_example.utils.vo

sealed class Either<out Failed, out Result> {
    data class Success<out R>(val body: R): Either<Nothing,R>()
    data class Error<out L>(val failure: L): Either<L,Nothing>()
}