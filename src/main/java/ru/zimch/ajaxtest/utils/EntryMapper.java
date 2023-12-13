package ru.zimch.ajaxtest.utils;

import org.springframework.stereotype.Component;
import ru.zimch.ajaxtest.dto.EntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;


/**
 * Маппер для DTO, используемого для отрисовки
 */
@Component
public class EntryMapper implements AbstractEntryMapper<PhoneBookEntry, EntryDTO> {
    @Override
    public EntryDTO mapToDTO(PhoneBookEntry entity) {
        return new EntryDTO(
                entity.getId(),
                entity.getSubscriberName(),
                entity.getPhoneNumber(),
                entity.getLastModifiedDate()
        );
    }

    @Override
    public PhoneBookEntry mapToEntity(EntryDTO dto) {
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
        phoneBookEntry.setId(dto.getEntryId());
        phoneBookEntry.setSubscriberName(dto.getSubscriberName());
        phoneBookEntry.setPhoneNumber(dto.getPhoneNumber());
        phoneBookEntry.setLastModifiedDate(dto.getLastModifiedDate());
        return phoneBookEntry;
    }
}
