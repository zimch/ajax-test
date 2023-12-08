package ru.zimch.ajaxtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zimch.ajaxtest.models.PhoneBookEntry;

@Repository
public interface PhoneBookEntryRepository extends JpaRepository<PhoneBookEntry, Long> {

}
