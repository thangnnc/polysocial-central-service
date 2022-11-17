package com.polysocial.service.notifications;

import java.util.List;

import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Members;
import com.polysocial.entity.Notifications;

public interface NotificationsService {

    NotificationsDTO createNoti(NotificationsDTO notiDTO);

    NotificationsDTO updateNoti(NotificationsDTO notiDTO);

    void deleteNoti(Long notiId);

    List<Members> createMoreNoti(NotificationsDTO notiDTO, Long groupId);

    List<NotificationsDTO> getNotiByUserId(Long userId);
}
