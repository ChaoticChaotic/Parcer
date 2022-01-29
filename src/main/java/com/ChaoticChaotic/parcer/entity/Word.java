package com.ChaoticChaotic.parcer.entity;

import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import lombok.*;


import javax.persistence.*;


@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false, unique = true)
    private String value;

    @Column(name = "repeats_counter", nullable = false)
    private Integer repeats;

    @Column(name = "url_address")
    private String urlAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private SupportedLanguages language;
    
}
