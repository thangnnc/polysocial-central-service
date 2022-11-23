package com.polysocial.service.impl.notifications;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Members;
import com.polysocial.entity.Notifications;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.NotificationsRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.notifications.NotificationsService;

@Service
public class NotificationsImpl implements NotificationsService {

    @Autowired
    private NotificationsRepo notificationsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public NotificationsDTO createNoti(NotificationsDTO notiDTO) {
        Notifications noti = modelMapper.map(notiDTO, Notifications.class);
        noti.setUser(userRepo.findById(notiDTO.getUser()).get());
        notificationsRepo.save(noti);
        return notiDTO;
    }

    @Override
    public NotificationsDTO updateNoti(NotificationsDTO notiDTO) {
        Notifications noti = modelMapper.map(notiDTO, Notifications.class);
        noti = notificationsRepo.save(noti);
        return modelMapper.map(noti, NotificationsDTO.class);
    }

    @Override
    public void deleteNoti(Long notiId) {
        notificationsRepo.deleteById(notiId);
    }

    @Override
    public List<Members> createMoreNoti(NotificationsDTO notiDTO, Long groupId) {
        List<Members> members = memberRepo.findByGroupId(groupId);
        for (Members member : members) {
            Notifications noti = modelMapper.map(notiDTO, Notifications.class);
            noti.setUser(userRepo.findById(member.getUserId()).get());
            notificationsRepo.save(noti);
        }
        return members;
    }

    @Override
    public List<NotificationsDTO> getNotiByUserId(Long userId) {
        List<Notifications> notis = notificationsRepo.findByUserId(userId);
        return modelMapper.map(notis, List.class);
    }

    @Override
    public List<NotificationsDTO> getAllNotifications(Long userId) {
        List<Notifications> notis = notificationsRepo.findByUserId(userId);
        return modelMapper.map(notis, List.class);
    }

    
}
