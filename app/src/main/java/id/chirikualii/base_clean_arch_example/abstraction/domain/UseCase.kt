package id.chirikualii.base_clean_arch_example.abstraction.domain

abstract class UseCase <in Params, out Results>{
    abstract suspend fun run (params: Params) :Results
    object None
}