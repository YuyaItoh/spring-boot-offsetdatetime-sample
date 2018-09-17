package com.example.domain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.AttributeConverter;

import org.springframework.data.convert.Jsr310Converters.DateToLocalDateTimeConverter;
import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;

/**
 * Hibernate ではなく、自前で用意した OffsetDateTime の Converter。
 * Hibernate の機能を利用した場合との違いは以下
 *
 * <ul>
 * <li>log4jdbc-log4j2 で出力したときの時刻が、 Hibernate だと Timezone.getDefault に対して OffsetDateTime は UTC</li>
 * <li>DB から取得したときのタイムゾーン（Offset）が、 Hibernate だと Timezone.getDefault に対して OffsetDateTime は UTC</li>
 * </ul>
 *
 * 自作の AttributeConverter を作成せずに Hibernate の機能を利用したほうが良いのではないか。
 * Hibernate 5.3（JPA 2.2）からは OffsetDateTime のマッピングは標準機能になっているので、独自実装によるロックインもない。
 */
public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(OffsetDateTime attribute) {
        // UTC に変換して DB に格納する
        return attribute == null ? null : LocalDateTimeToDateConverter.INSTANCE.convert(attribute.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(Date dbData) {
        // DBの時刻は UTC として取得する
        return dbData == null ? null : DateToLocalDateTimeConverter.INSTANCE.convert(dbData).atOffset(ZoneOffset.UTC);
    }
}
