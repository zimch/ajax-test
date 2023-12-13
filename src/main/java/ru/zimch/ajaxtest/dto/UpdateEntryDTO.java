package ru.zimch.ajaxtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO, используемы для обновления существующей или создания новой записи в таблице
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEntryDTO implements AbstractEntryDTO {

    private String subscriberName;
    private String phoneNumber;

}
