import java.math.BigDecimal

class Colaborador(
    val nome: String = "",
    val salario: BigDecimal = 0.0.toBigDecimal()
){
    override fun toString(): String {
        return "$nome, $salario"
    }
}