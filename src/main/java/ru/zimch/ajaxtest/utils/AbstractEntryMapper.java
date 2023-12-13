package ru.zimch.ajaxtest.utils;

import ru.zimch.ajaxtest.dto.AbstractEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;

/**
 * Абстрактный маппер, предназдаченный для работы со всеми реализациями DTO для {@link PhoneBookEntry}
 */
public interface AbstractEntryMapper<T extends PhoneBookEntry, V extends AbstractEntryDTO> {

    /**
     * Маппим из сущности в DTO
     * @param entity сущность
     * @return DTO, полученная из сущности
     */
    V mapToDTO(T entity);

    /**
     * Маппим из DTO в сущность
     * @param dto необходимый DTO
     * @return сущность, связанная с этим DTO
     */
    T mapToEntity(V dto);
}
