package ar.edu.unsam.algo2

import java.awt.image.TileObserver
import java.time.LocalDate

class Jefe(
    val tareas : MutableList<Tarea> = mutableListOf(),
    var dineroRecaudado: Double = 0.0,
    val bandas : MutableList<Banda> = mutableListOf(),
    val observers: MutableList<ObserverTarea> = mutableListOf()
) {

    fun recibirDinero(monto: Double) {
        dineroRecaudado += dineroRecaudado
    }

    fun darDinero(monto: Double) {
        dineroRecaudado -= dineroRecaudado
    }

    fun generarCobrarCuota(monto: Double) {
        tareas.add(CobrarCuota(fecha = LocalDate.now(), monto / 2))
        tareas.add(CobrarCuota(fecha = LocalDate.now().plusMonths(1), monto / 2))
        tareas.add(CobrarCuota(fecha = LocalDate.now().plusMonths(2), monto / 2))
        tareas.add(CobrarCuota(fecha = LocalDate.now().plusMonths(3), monto / 2))
    }

    fun agregarTarea(tarea: Tarea) { tareas.add(tarea)}

    fun asignarTodasLasTareas(){ tareas.forEach({asignarTarea(it)})}

    fun asignarTarea(tarea: Tarea) {
        if (hayBandasQueQuieranTarea(tarea)) {
            bandas.find { it.cumplirTarea(tarea) }?.agregarTarea(tarea)
        } else {
            realizarCambios()
        }
    }

    fun hayBandasQueQuieranTarea(tarea: Tarea) : Boolean = bandas.find {it.cumplirTarea(tarea)} != null

    fun realizarCambios(): Unit {
        //TODO
    }

    fun ejecutarTarea(){
        bandas.forEach({
            it.ejecutarTarea(this)
            observers.forEach({
                it.observar()
            })
        })
    }


}
