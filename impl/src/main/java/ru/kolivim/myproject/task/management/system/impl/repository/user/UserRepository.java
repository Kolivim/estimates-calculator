package ru.kolivim.myproject.task.management.system.impl.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User>  /*JpaRepository<User, UUID> */{
    Optional<User> findByIdAndIsDeletedFalse(UUID id);

//    Optional<User> findFirstByEmail(UUID id);

    Optional<User> findByLogin(String login);
    Optional<User> findById(UUID id);
    Optional<User> findByLoginAndIsDeletedFalse(String login);
    Page<User> findAllByFullnameLikeAndIsDeletedFalse(String fullName, Pageable pageable);
    Page<User> findAllByBirthDateAfterAndIsDeletedFalse(ZonedDateTime birthDate, Pageable pageable);

    /*
    @Transactional
    @Query(value = "select tag.id, tag.name, tag.is_deleted, count(*) count from tag " +
            " join skillbox.post_tag pt on tag.id = pt.tag_id\n" +
            " join skillbox.post p on p.id = pt.post_id" +
            " where tag.name like ?1% and p.is_deleted = false" +
            " group by tag.id\n" +
            " order by count desc limit 5", nativeQuery = true)
    List<Tag> findAllByName(String name);

      @Modifying
    @Query(value = """
            INSERT INTO friend (id, account_from, account_to, status_code, is_deleted)
            VALUES (uuid_generate_v4(), :accountFrom, :accountTo, :statusCode, 'false')
            ON CONFLICT (account_from, account_to)
            DO UPDATE SET status_code = :statusCode, is_deleted = 'false'""", nativeQuery = true)
    void addRelationship(@Param("accountFrom") UUID accountFrom, @Param("accountTo") UUID accountTo
            , @Param("statusCode") String statusCode);

    @Query(value = """
            SELECT account_from AS id, COUNT(account_to) AS rating
                FROM friend
            WHERE status_code = 'FRIEND' AND account_from <> :current_user
                AND NOT is_deleted
                AND NOT account_from IN (SELECT account_from FROM friend WHERE
                                            account_to = :current_user AND NOT is_deleted)
                AND account_to IN (SELECT account_to FROM friend WHERE status_code = 'FRIEND'
                                        AND account_from = :current_user AND NOT is_deleted)
            GROUP BY account_from
            ORDER BY COUNT(account_to) DESC LIMIT 10"""
            , nativeQuery = true)
    List<Object[]> findAllOrderedByNumberFriends(@Param("current_user") UUID id);




              INSERT INTO notification_settings (id, account_id, is_deleted, enable_like, enable_post, enable_post_comment, enable_comment_comment, enable_message, enable_friend_request, enable_friend_birthday, enable_send_email_message)
            SELECT gen_random_uuid(), id, false, true, true, true, true, true, true, true, true
            FROM (
                     SELECT users.id
                     FROM users
                     EXCEPT
                     SELECT notification_settings.account_id
                     FROM notification_settings
                 ) as id;
    */


}
