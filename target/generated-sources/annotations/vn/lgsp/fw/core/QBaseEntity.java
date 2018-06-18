package vn.lgsp.fw.core;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity<?>> {

    private static final long serialVersionUID = -2033847258L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final QAuditable _super = new QAuditable(this);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> ngaySua = _super.ngaySua;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> ngayTao = _super.ngayTao;

    public final StringPath nguoiSua = createString("nguoiSua");

    public final StringPath nguoiTao = createString("nguoiTao");

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(String variable) {
        super((Class) BaseEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(Path<? extends BaseEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(PathMetadata metadata) {
        super((Class) BaseEntity.class, metadata);
    }

}

