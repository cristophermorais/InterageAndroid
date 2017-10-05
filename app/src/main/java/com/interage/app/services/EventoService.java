package com.interage.app.services;

import com.interage.app.interage.R;
import com.interage.app.model.Evento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristopher Morais on 24/09/2017.
 */

public class EventoService {

    public static List<Evento> getEventosDestaque() {
        List<Evento> eventoList = new ArrayList<>();
        int[] images = new int[]{
                R.drawable.evento1,
                R.drawable.evento2,
                R.drawable.evento3,
                R.drawable.evento4,
                R.drawable.evento5,
                R.drawable.evento6,
                R.drawable.evento7,
                R.drawable.evento8};

        for (int i = 0; i < images.length; i++) {
            eventoList.add(new Evento("Event "+i, images[i]));
        }
        return eventoList;
    }

    public static List<Evento> getEventosAtivos(BigDecimal codUsuario) {
        List<Evento> eventoList = new ArrayList<>();
        int[] images = new int[]{
                R.drawable.evento1,
                R.drawable.evento2,
                R.drawable.evento3,
                R.drawable.evento4};

        for (int i = 0; i < images.length; i++) {
            eventoList.add(new Evento("Event "+i, images[i]));
        }
        return eventoList;
    }

}
