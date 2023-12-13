package ru.zimch.ajaxtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Основной DTO, используем для отрисовки всех записей в таблице
 *
 * <p>
 *     p.s. можно было сделать в качестве <b>record</b>,
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDTO implements AbstractEntryDTO {

    private Long entryId;
    private String subscriberName;
    private String phoneNumber;

    @JsonFormat(pattern = "EEE, d MMM yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;

}
