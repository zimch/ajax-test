package ru.zimch.ajaxtest.services;

import ru.zimch.ajaxtest.models.PhoneBookEntry;

/**
 * Интерфейс, определяющий сервисный уровень работы с записями в телефонной книге
 */
public interface PhoneBookEntryService {

    /**
     * Обновляет данные о записи в телефонной книге
     * @param id идентификатор обновляемой записи
     * @param entry запись, на которую обновляем
     * @return true, если обновление успешно; false - в другом случае
     */
    boolean updateEntry(long id, PhoneBookEntry entry);

    /**
     * Сохраняет новую запись в книге
     * @param entry запись, которую сохраняем
     * @return true, если сохранение успешно; false - в другом случае
     */
    boolean saveNewEntry(PhoneBookEntry entry);

    /**
     * Удаляем запись по id
     * @param id идентификатор удаляемой записи
     * @return true, если удаление успешно; false - в другом случае
     */
    boolean deleteEntry(long id);

}
