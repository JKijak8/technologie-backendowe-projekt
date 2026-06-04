package com.jkpbmz.technologiebackendoweprojekt.projections.load;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoadUpdateRequest extends LoadSaveRequest {
    private String deliveryTime;
    private ZonedDateTime sendDate;
    private ZonedDateTime deliveryDate;
}
