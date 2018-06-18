package vn.lgsp.fw.core;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditable is a Querydsl query type for Auditable
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAuditable extends EntityPathBase<Auditable<?>> {

    private static final long serialVersionUID = 859800419L;

    public static final QAuditable auditable = new QAuditable("auditable");

    public final DateTimePath<java.time.LocalDateTime> ngaySua = createDateTime("ngaySua", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> ngayTao = createDateTime("ngayTao", java.time.LocalDateTime.class);

    public final SimplePath<Object> nguoiSua = createSimple("nguoiSua", Object.class);

    public final SimplePath<Object> nguoiTao = createSimple("nguoiTao", Object.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAuditable(String variable) {
        super((Class) Auditable.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAuditable(Path<? extends Auditable> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAuditable(PathMetadata metadata) {
        super((Class) Auditable.class, metadata);
    }

}

