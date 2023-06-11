
package com.proyectoasistencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ControlAsistencia {


    public static void main(String[] args) {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("hh:mma");
        String hora = horaActual.format(formatterH);
        LocalTime horaEntrada = LocalTime.of(9,10);
        boolean esDespues = horaActual.isAfter(horaEntrada);
        if(esDespues){
            System.out.println("Llego tarde");
        }
}
    
}
