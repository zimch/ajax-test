package ru.zimch.ajaxtest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


/**
 * Класс - сущность, определяющий запись в телефонной книге
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "entry")
public class PhoneBookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_name")
    private String subscriberName;

    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Отвечает сразу за дату создания (если не обновлялся), и за дату обновления.
     *
     * <p>
     *     P.S. не решил, делать две даты (отвечающие каждая за свое) или одну, поэтому так..
     * </p>
     */
    @Column(name = "last_mod_time")
    @UpdateTimestamp
    @JsonFormat(pattern = "EEE, d MMM yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public PhoneBookEntry(String subscriberName, String phoneNumber) {
        this.subscriberName = subscriberName;
        this.phoneNumber = phoneNumber;
    }

    public PhoneBookEntry(String subscriberName, String phoneNumber, LocalDateTime lastModifiedDate) {
        this.subscriberName = subscriberName;
        this.phoneNumber = phoneNumber;
        this.lastModifiedDate = lastModifiedDate;
    }


}
