package com.cocoon.cop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSteamApi is a Querydsl query type for SteamApi
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSteamApi extends EntityPathBase<SteamApi> {

    private static final long serialVersionUID = 610412064L;

    public static final QSteamApi steamApi = new QSteamApi("steamApi");

    public final NumberPath<Long> appid = createNumber("appid", Long.class);

    public final StringPath genres = createString("genres");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final DatePath<java.time.LocalDate> release_date = createDate("release_date", java.time.LocalDate.class);

    public final StringPath steamspy_tags = createString("steamspy_tags");

    public QSteamApi(String variable) {
        super(SteamApi.class, forVariable(variable));
    }

    public QSteamApi(Path<? extends SteamApi> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSteamApi(PathMetadata metadata) {
        super(SteamApi.class, metadata);
    }

}

