package ar.edu.unsam.algo2

abstract class Banda(
    val integrantes : MutableList<Integrante> = mutableListOf(),
) {
    var miembroPrincipal: Integrante = integrantes.first()
    var montoBanda: Double = 0.0
    var montoDeJefe: Double = 0.0
    var tareaAsignada: MutableList<Tarea> = mutableListOf()
    var porcentajebanda = 0.2
    var porcentajeJefe = 0.8

    fun recibirDinero(monto: Double, jefe: Jefe): Unit {
        montoBanda += monto * porcentajebanda
        jefe.recibirDinero(monto * porcentajeJefe)
    }

    fun pagar(monto: Double) {
        montoDeJefe -= monto
    }

    fun cumplirTarea(tarea: Tarea): Boolean = montoBanda > 0 && this.integrantesQuieren(tarea)

    abstract fun integrantesQuieren(tarea: Tarea): Boolean

    fun agregarTarea(tarea: Tarea) {
        tareaAsignada.add(tarea)
    }

    fun ejecutarTarea(jefe: Jefe) { tareaAsignada.first().ejecutar(this, jefe)}

    fun respartirDinero(monto: Double){
        val botin = monto / integrantes.size
        integrantes.forEach({it.recibirDinero(botin)}
        )}
}

class Forajida() : Banda(){
    override fun integrantesQuieren(tarea: Tarea): Boolean = integrantes.any({ it.personalidad.quiereTarea(tarea) })
}

class  Sorora() : Banda(){
    override fun integrantesQuieren(tarea: Tarea): Boolean = integrantes.all({it.personalidad.quiereTarea(tarea)})
}

class Tipica() : Banda(){
    override fun integrantesQuieren(tarea: Tarea): Boolean = miembroPrincipal.personalidad.quiereTarea(tarea)

}

