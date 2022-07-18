
import java.io.File
import java.math.RoundingMode

var colaborador = Colaborador()
var referencia: MutableMap<Int, Colaborador> = mutableMapOf(0 to colaborador)
var especializada: MutableMap<Int, Colaborador> = mutableMapOf(0 to colaborador)
var diferenca = 0.0.toBigDecimal()
var diferencaTotal = 0.0.toBigDecimal()
var contador = 0

fun main(){
    compararArquivos()
}

fun compararArquivos() {

    val arquivoReferencia = "src/main/resources/salariosReferencia.csv"

    var leitorReferencia = File(arquivoReferencia).readLines()

    for(linha in leitorReferencia){
        val propriedades = linha.split(";")
        var id = propriedades[0].toInt()
        colaborador = Colaborador(nome = propriedades[1], salario = propriedades[2].toBigDecimal())
        referencia[id] = colaborador
    }

    val arquivoEmpresaEspecializada = "src/main/resources/salariosEmpresaEspecializada.csv"

    var leitorEspecializada = File(arquivoEmpresaEspecializada).readLines()

    for(line in leitorEspecializada){
        val propriedades = line.split(";")
        var id = propriedades[0].toInt()
        colaborador = Colaborador(nome = propriedades[1], salario = propriedades[2].toBigDecimal())
        especializada[id] = colaborador
    }

    for (index in referencia.keys){
        if (referencia[index+1]?.salario != especializada[index+1]?.salario){
            if (referencia[index+1]?.salario!! > especializada[index+1]?.salario!!){
                diferenca = (referencia[index+1]?.salario!! - especializada[index+1]?.salario!!)
            }else{
                diferenca = (especializada[index+1]?.salario!! - referencia[index+1]?.salario!!)
            }
            diferencaTotal += diferenca
            contador++
            println("Colaborador ${especializada[index+1]?.nome} está com salário errado e " +
                    "a diferença é de R$ ${diferenca.setScale(2, RoundingMode.UP)}")
        }
    }
    println()
    println("A difença entre o valor total da folha de referência " +
            "e o valor da folha enviada pela empresa especializada é de" +
            " R$ ${diferencaTotal.setScale(2, RoundingMode.UP)}")

    println()
    val mediaDiferenca = diferencaTotal/(contador.toBigDecimal())
    println("A diferença média entre os valores foi de " +
            "R$ ${mediaDiferenca.setScale(2, RoundingMode.UP)}")

}

