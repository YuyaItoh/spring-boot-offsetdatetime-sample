package com.example.domain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.data.convert.Jsr310Converters.DateToLocalDateTimeConverter;
import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;

@Converter(autoApply = true)
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
