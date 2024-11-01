package org.springproject.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.library.entity.ReserveEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDto {
    private String idReserve;
    private String idUser;
    private String idBook;
    private Date reservationStartDate;
    private Date reservationEndDate;
    private String stateReserve;

    public ReserveDto(ReserveEntity reserve){
        this.idReserve = reserve.getIdReserve();
        this.idUser = reserve.getIdUser();
        this.idBook = reserve.getIdBook();
        this.reservationStartDate = reserve.getReservationStartDate();
        this.reservationEndDate = reserve.getReservationEndDate();
        this.stateReserve = reserve.getStateReserve();
    }
}
