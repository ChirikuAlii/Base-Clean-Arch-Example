package id.chirikualii.base_clean_arch_example.abstraction.core

interface BaseMapper<Raw, Domain> {
    fun mapToDomain(raw: Raw): Domain
    fun mapToRaw(domain: Domain): Raw
}