package ar.edu.unsam.algo2

import java.time.LocalDate

interface Personalidad {
    fun quiereTarea(tarea: Tarea): Boolean
}

class AltoPerfil : Personalidad{
    override fun quiereTarea(tarea: Tarea): Boolean = tarea.monto * 0.2 > 1000
}

class Culposos: Personalidad{
    override fun quiereTarea(tarea: Tarea): Boolean = tarea.comerciante.recaudacionMensual > 5000
}

class Alternantes(val altoPerfil : AltoPerfil, val culposo : Culposos) : Personalidad{
    override fun quiereTarea(tarea: Tarea): Boolean =
        if(tarea.fecha.month.value % 2 == 0) culposo.quiereTarea(tarea)
        else altoPerfil.quiereTarea(tarea)
}

class Cabuleros() : Personalidad{
    override fun quiereTarea(tarea: Tarea): Boolean = tarea.comerciante.nombre.contains("x")
}

class Combinadas(val listaPersonalidad : MutableList<Personalidad>) : Personalidad{
    override fun quiereTarea(tarea: Tarea): Boolean = listaPersonalidad.all { it.quiereTarea(tarea) }
}


