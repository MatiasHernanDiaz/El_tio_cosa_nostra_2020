package ar.edu.unsam.algo2

class Integrante(
    var personalidad : Personalidad,
    var salario: Double = 0.0
) {
    fun recibirDinero(monto: Double){ salario += monto}
}
