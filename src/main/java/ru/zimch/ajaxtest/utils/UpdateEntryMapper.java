package ru.zimch.ajaxtest.utils;

import org.springframework.stereotype.Component;
import ru.zimch.ajaxtest.dto.UpdateEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;

@Component
public class UpdateEntryMapper implements AbstractEntryMapper<PhoneBookEntry, UpdateEntryDTO> {
    @Override
    public UpdateEntryDTO mapToDTO(PhoneBookEntry entity) {
        return new UpdateEntryDTO(
                entity.getSubscriberName(),
                entity.getPhoneNumber()
        );
    }

    @Override
    public PhoneBookEntry mapToEntity(UpdateEntryDTO dto) {
        return new PhoneBookEntry(
                dto.getSubscriberName(),
                dto.getPhoneNumber()
        );
    }
}
