package com.zeeroam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusProviderDTO {
    private Long id;
    private String name;
    private String contact;
    private String email;
}
