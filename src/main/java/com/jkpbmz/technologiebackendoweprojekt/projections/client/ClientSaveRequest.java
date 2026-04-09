package com.jkpbmz.technologiebackendoweprojekt.projections.client;

import lombok.Data;

@Data
public class ClientSaveRequest {
    private String name;
    private String nip;
    private String phoneNumber;
    private String email;
}
