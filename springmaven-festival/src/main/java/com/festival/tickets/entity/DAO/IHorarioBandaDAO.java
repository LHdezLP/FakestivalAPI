package com.festival.tickets.entity.DAO;

import com.festival.tickets.entity.models.FranjaHoraria;
import com.festival.tickets.entity.models.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.festival.tickets.entity.models.HorarioBanda;
import org.springframework.data.repository.query.Param;

public interface IHorarioBandaDAO extends CrudRepository<HorarioBanda, Long> {
    @Query("SELECT COUNT(hb) > 0 FROM HorarioBanda hb WHERE hb.horario = :horario AND hb.franjaHoraria = :franjaHoraria")
    boolean existsByHorarioAndFranjaHoraria(@Param("horario") Horario horario, @Param("franjaHoraria") FranjaHoraria franjaHoraria);

    // Verifica si ya existe un HorarioBanda con el mismo Horario y FranjaHoraria, excluyendo un ID específico
    @Query("SELECT COUNT(hb) > 0 FROM HorarioBanda hb WHERE hb.horario = :horario AND hb.franjaHoraria = :franjaHoraria AND hb.id_horario_banda != :idHorarioBanda")
    boolean existsByHorarioAndFranjaHorariaAndIdHorarioBandaNot(
            @Param("horario") Horario horario,
            @Param("franjaHoraria") FranjaHoraria franjaHoraria,
            @Param("idHorarioBanda") Long idHorarioBanda
    );
}
