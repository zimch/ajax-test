package ru.zimch.ajaxtest.services;

import ru.zimch.ajaxtest.dto.AbstractEntryDTO;
import ru.zimch.ajaxtest.dto.EntryDTO;
import ru.zimch.ajaxtest.dto.UpdateEntryDTO;
import ru.zimch.ajaxtest.models.PhoneBookEntry;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, определяющий сервисный уровень работы с записями в телефонной книге
 */
public interface PhoneBookEntryService {

    /**
     * Возвращает список всех записей в книге (в виде DTO)
     *
     * @return список DTO, связанный с записями в книге
     */
    List<AbstractEntryDTO> findAllEntry();

    /**
     * Обновляет данные о записи в телефонной книге
     * @param id идентификатор обновляемой записи
     * @param entry запись, на которую обновляем
     * @return true, если обновление успешно; false - в другом случае
     */
    Optional<PhoneBookEntry> updateEntry(long id, UpdateEntryDTO entry);

    /**
     * Сохраняет новую запись в книге
     * @param entry запись, которую сохраняем
     * @return true, если сохранение успешно; false - в другом случае
     */
    Optional<PhoneBookEntry> saveNewEntry(UpdateEntryDTO entry);

    /**
     * Удаляем запись по id
     * @param id идентификатор удаляемой записи
     * @return true, если удаление успешно; false - в другом случае
     */
    Optional<PhoneBookEntry> deleteEntry(long id);

}
