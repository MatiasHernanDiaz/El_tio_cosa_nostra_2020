package ar.edu.unsam.algo2

import java.time.LocalDate

abstract class Tarea {
    var cumplida: Boolean = false
    abstract val fecha: LocalDate
    open var monto : Double = 0.0
    lateinit var comerciante: Comerciante
    abstract fun ejecutar(banda: Banda, jefe: Jefe): Unit
}

class RecolectarDinero(override val fecha: LocalDate) : Tarea(){
    override var monto = comerciante.recaudacionMensual * 0.1
    override fun ejecutar(banda: Banda, jefe: Jefe) {
        banda.recibirDinero(monto, jefe)
    }
}

class AbrirDeposito(override val fecha: LocalDate) : Tarea(){
    override fun ejecutar(banda: Banda, jefe: Jefe) {
        banda.pagar(comerciante.metrosCuadrados * 100)
    }
}

class PrestarDinero(override val fecha: LocalDate, var prestamo : Double) : Tarea(){
    override fun ejecutar(banda: Banda, jefe: Jefe) {
        jefe.darDinero(prestamo)
        jefe.generarCobrarCuota(prestamo)
    }
}

class CobrarCuota(override val fecha: LocalDate, override var monto: Double) : Tarea(){
    override fun ejecutar(banda: Banda, jefe: Jefe) {
        banda.recibirDinero(monto, jefe)
    }
}

