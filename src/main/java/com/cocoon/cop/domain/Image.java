package com.cocoon.cop.domain;

import com.cocoon.cop.domain.base.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Image extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "client_filename")
    private String clientFilename;

    @Column(name = "server_filename")
    private String serverFilename;

    @OneToOne(mappedBy = "image")
    private Member member;

}
