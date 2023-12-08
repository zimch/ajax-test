package ru.zimch.ajaxtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zimch.ajaxtest.models.PhoneBookEntry;
import ru.zimch.ajaxtest.repositories.PhoneBookEntryRepository;
import ru.zimch.ajaxtest.services.PhoneBookEntryServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Контроллер для работы с записями в телефонной книге
 */
@RestController
@RequestMapping("/api/entry")
public class EntryController {

    private final PhoneBookEntryRepository entryRepository;
    private final PhoneBookEntryServiceImpl entryService;

    @Autowired
    public EntryController(PhoneBookEntryRepository entryRepository, PhoneBookEntryServiceImpl entryService) {
        this.entryRepository = entryRepository;
        this.entryService = entryService;
    }

    @GetMapping("")
    public List<PhoneBookEntry> getAllEntries() {
        return entryRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<PhoneBookEntry> createNewEntry(@RequestBody PhoneBookEntry entry) {
        if (entryService.saveNewEntry(entry)) return new ResponseEntity<>(entry, HttpStatus.CREATED);

        return new ResponseEntity<>(entry, HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneBookEntry> updateEntry(@PathVariable Long id, @RequestBody PhoneBookEntry entry) {
        if (entryService.updateEntry(id, entry)) {
            Optional<PhoneBookEntry> modifiedEntryOptional = entryRepository.findById(id);
            if (modifiedEntryOptional.isPresent()) return new ResponseEntity<>(modifiedEntryOptional.get(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(entry, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEntry(@PathVariable Long id) {
        if (entryService.deleteEntry(id)) {
            return new ResponseEntity<>(Map.of("Info", "Entry deleted"), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Map.of("Error", "ID doesn't exist"), HttpStatus.NOT_FOUND);
    }

}
