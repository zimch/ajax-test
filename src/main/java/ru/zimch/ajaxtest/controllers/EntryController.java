package ru.zimch.ajaxtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zimch.ajaxtest.dto.AbstractEntryDTO;
import ru.zimch.ajaxtest.dto.UpdateEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;
import ru.zimch.ajaxtest.services.PhoneBookEntryServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с записями в телефонной книге
 */
@RestController
@RequestMapping("/api/entry")
public class EntryController {

    private final PhoneBookEntryServiceImpl entryService;

    @Autowired
    public EntryController(PhoneBookEntryServiceImpl entryService) {
        this.entryService = entryService;
    }

    @GetMapping("")
    public List<AbstractEntryDTO> getAllEntries() {
        return entryService.findAllEntry();
    }

    @PostMapping("")
    public ResponseEntity<PhoneBookEntry> createNewEntry(@RequestBody UpdateEntryDTO entry) {
        Optional<PhoneBookEntry> phoneBookEntryOptional = entryService.saveNewEntry(entry);

        return phoneBookEntryOptional.map(phoneBookEntry ->
                new ResponseEntity<>(phoneBookEntry, HttpStatus.CREATED)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneBookEntry> updateEntry(@PathVariable Long id, @RequestBody UpdateEntryDTO entry) {
        Optional<PhoneBookEntry> phoneBookEntryOptional = entryService.updateEntry(id, entry);

        return phoneBookEntryOptional.map(phoneBookEntry ->
                new ResponseEntity<>(phoneBookEntry, HttpStatus.CREATED)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneBookEntry> deleteEntry(@PathVariable Long id) {
        Optional<PhoneBookEntry> phoneBookEntryOptional = entryService.deleteEntry(id);

        return phoneBookEntryOptional.map(entry ->
                new ResponseEntity<>(entry, HttpStatus.NO_CONTENT)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
