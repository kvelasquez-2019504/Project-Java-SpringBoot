package org.springproject.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reserves")
public class ReserveEntity {
    @Id
    private String idReserve;
    private String idUser;
    private String idBook;
    private Date reservationStartDate;
    private Date reservationEndDate;
    private String stateReserve;
}
