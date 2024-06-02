package ar.edu.unsam.algo2

import java.time.LocalDate

interface ObserverTarea {
    fun observar()
}

class NotificarWpp(val mensaje: MensajeWpp) : ObserverTarea{
    lateinit var sendWpp : SendWpp
    override fun observar() {
        sendWpp.sendWapp(mensaje)
    }
}

interface  SendWpp{
    fun sendWapp(mensaje: MensajeWpp)
}

data class MensajeWpp(val tel: Int = 156598343, val cuerpo : String = "La puerca está en la pocilga - $ 500")

class NotificarAfip(val datos: DatosAfip, val banda: Banda) : ObserverTarea{
    lateinit var sendAfip: SendAfip
    override fun observar() {
        if(banda.montoBanda > 1000000) sendAfip.sendAfip(datos)
    }
}

interface SendAfip{
    fun sendAfip(datos : DatosAfip)
}

data class DatosAfip(val fecha: LocalDate, val tipoMovimiento: Int, val concepto: String = "VARIOS")

class AumentarPorcentaje(val banda: Banda) : ObserverTarea{
    override fun observar() {
        val botin = banda.montoBanda * 0.7
        banda.montoBanda = banda.montoBanda * 0.3
        banda.respartirDinero(botin)
    }

}