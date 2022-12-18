package com.polysocial.service.notifications;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Members;

public interface NotificationsService {

    NotificationsDTO createNoti(NotificationsDTO notiDTO);

    NotificationsDTO updateNoti(NotificationsDTO notiDTO);

    void deleteNoti(Long notiId);

    List<Members> createMoreNoti(NotificationsDTO notiDTO, Long groupId);

    List<NotificationsDTO> getNotiByUserId(Long userId);

    Page<NotificationsDTO> getAllNotifications(Long userId, Pageable pageable);

    NotificationsDTO updateOneStatus(Long userId, Long notiId);

    List<NotificationsDTO> updateAllStatus(Long userId);
}
