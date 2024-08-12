package ru.kolivim.estimates.calculator.domain.price;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ru.kolivim.estimates.calculator.domain.base.BaseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price")
public class Price extends BaseEntity {

    @Column(name="price_list_id")
    private UUID priceListId;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

    @Column(name="description")
    private String description;

    @Column(name="last_author_id")
    private UUID lastAuthorId;

    @Column(name="last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @Column(name="version")
    private Integer version;


    /*
                <column name="id" type="uuid"/>
                <column name="is_deleted" type="boolean"/>
                <column name="price_list_id" type="uuid"/>
                <column name="name" type="varchar(255)"/>
                <column name="price" type="NUMERIC(16,2)"/>
                <column name="description" type="MEDIUMTEXT"/>
                <column name="last_author_id" type="uuid"/>
                <column name="last_modified_date" type="timestamp(6) with time zone"/>
                <column name="version" type="integer"/>
    */

}
