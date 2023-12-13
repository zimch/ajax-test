package ru.zimch.ajaxtest.services;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimch.ajaxtest.dto.AbstractEntryDTO;
import ru.zimch.ajaxtest.dto.UpdateEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;
import ru.zimch.ajaxtest.repositories.PhoneBookEntryRepository;
import ru.zimch.ajaxtest.utils.EntryMapper;
import ru.zimch.ajaxtest.utils.UpdateEntryMapper;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class PhoneBookEntryServiceImpl implements PhoneBookEntryService {

    private final PhoneBookEntryRepository entryRepository;
    private final EntryMapper entryMapper;
    private final UpdateEntryMapper updateEntryMapper;

    @Autowired
    public PhoneBookEntryServiceImpl(PhoneBookEntryRepository entryRepository,
                                     EntryMapper entryMapper,
                                     UpdateEntryMapper updateEntryMapper) {
        this.entryRepository = entryRepository;
        this.entryMapper = entryMapper;
        this.updateEntryMapper = updateEntryMapper;
    }

    @Override
    public List<AbstractEntryDTO> findAllEntry() {
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PhoneBookEntry> updateEntry(long id, UpdateEntryDTO entry) {
        Optional<PhoneBookEntry> entryOptional = entryRepository.findById(id);

        if (entryOptional.isEmpty() || (entry.getPhoneNumber() == null && entry.getSubscriberName() == null)) return Optional.empty();

        PhoneBookEntry entryEntity = entryOptional.get();

        setIfNotEmpty(entry.getPhoneNumber(), entryEntity::setPhoneNumber);
        setIfNotEmpty(entry.getSubscriberName(), entryEntity::setSubscriberName);

        entryRepository.save(entryEntity);

        return Optional.of(entryEntity);
    }

    @Override
    public Optional<PhoneBookEntry> saveNewEntry(UpdateEntryDTO entry) {
        if (entry.getPhoneNumber().isEmpty()) return Optional.empty();
        if (entry.getSubscriberName().isEmpty()) return Optional.empty();

        PhoneBookEntry bookEntry = updateEntryMapper.mapToEntity(entry);

        entryRepository.save(bookEntry);

        return Optional.of(bookEntry);
    }

    @Override
    public Optional<PhoneBookEntry> deleteEntry(long id) {
        Optional<PhoneBookEntry> entryOptional = entryRepository.findById(id);

        if (entryOptional.isEmpty()) return Optional.empty();

        entryRepository.delete(entryOptional.get());

        return entryOptional;
    }

    private void setIfNotEmpty(String value, Consumer<String> setter) {
        if (StringUtils.isNotBlank(value)) {
            setter.accept(value);
        }
    }
}
