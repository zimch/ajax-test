package ru.zimch.ajaxtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimch.ajaxtest.models.PhoneBookEntry;
import ru.zimch.ajaxtest.repositories.PhoneBookEntryRepository;

import java.util.Optional;

@Service
public class PhoneBookEntryServiceImpl implements PhoneBookEntryService {

    private final PhoneBookEntryRepository entryRepository;

    @Autowired
    public PhoneBookEntryServiceImpl(PhoneBookEntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public boolean updateEntry(long id, PhoneBookEntry entry) {
        Optional<PhoneBookEntry> entryOptional = entryRepository.findById(id);

        if (entryOptional.isEmpty()) return false;
        if (entry.getPhoneNumber() == null && entry.getSubscriberName() == null) return false;

        PhoneBookEntry entryEntity = entryOptional.get();

        // проверки на null - для работы без клиентской части
        if (entry.getPhoneNumber() != null) {
            if (!entry.getPhoneNumber().isEmpty()) entryEntity.setPhoneNumber(entry.getPhoneNumber());
            else entryEntity.setPhoneNumber(entryEntity.getPhoneNumber());
        }

        if (entry.getSubscriberName() != null) {
            if (!entry.getSubscriberName().isEmpty()) entryEntity.setSubscriberName(entry.getSubscriberName());
            else entryEntity.setSubscriberName(entryEntity.getSubscriberName());
        }

        entryRepository.save(entryEntity);

        return true;
    }

    @Override
    public boolean saveNewEntry(PhoneBookEntry entry) {
        if (entry.getPhoneNumber().isEmpty()) return false;
        if (entry.getSubscriberName().isEmpty()) return false;

        entryRepository.save(entry);

        return true;
    }

    @Override
    public boolean deleteEntry(long id) {
        Optional<PhoneBookEntry> entryOptional = entryRepository.findById(id);

        if (entryOptional.isEmpty()) return false;

        entryRepository.delete(entryOptional.get());

        return true;
    }
}
