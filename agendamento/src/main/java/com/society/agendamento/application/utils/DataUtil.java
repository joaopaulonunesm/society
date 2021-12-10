package com.society.agendamento.application.utils;

import java.time.LocalDateTime;

public class DataUtil {

    public static boolean isDataPassada(LocalDateTime data){
        return data.isBefore(LocalDateTime.now());
    }
}
