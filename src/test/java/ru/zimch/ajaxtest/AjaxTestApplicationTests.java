package ru.zimch.ajaxtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.zimch.ajaxtest.controllers.EntryController;
import ru.zimch.ajaxtest.dto.UpdateEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;
import ru.zimch.ajaxtest.services.PhoneBookEntryServiceImpl;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class AjaxTestApplicationTests {

    @Mock
    private PhoneBookEntryServiceImpl entryService;

    @InjectMocks
    private EntryController entryController;

    @Test
    void getAllEntries() throws Exception {
        when(entryService.findAllEntry()).thenReturn(Collections.emptyList());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entryController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/entry"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createNewEntry() throws Exception {
        UpdateEntryDTO entryDTO = new UpdateEntryDTO("John", "123");
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry("John", "123");

        when(entryService.saveNewEntry(any())).thenReturn(Optional.of(phoneBookEntry));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entryController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(entryDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.subscriberName").value("John"))
                .andExpect(jsonPath("$.phoneNumber").value("123"));
    }

    @Test
    void updateEntry() throws Exception {
        long entryId = 1;
        UpdateEntryDTO entryDTO = new UpdateEntryDTO("John", "123");
        PhoneBookEntry updatedEntry = new PhoneBookEntry("John", "123");

        when(entryService.updateEntry(entryId, entryDTO)).thenReturn(Optional.of(updatedEntry));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entryController).build();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/entry/{id}", entryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(entryDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.subscriberName").value("John"))
                .andExpect(jsonPath("$.phoneNumber").value("123"));
    }

    @Test
    void deleteEntry() throws Exception {
        long entryId = 1;
        PhoneBookEntry deletedEntry = new PhoneBookEntry("John", "123");

        when(entryService.deleteEntry(entryId)).thenReturn(Optional.of(deletedEntry));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entryController).build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/entry/{id}", entryId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
