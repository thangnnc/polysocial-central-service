package com.polysocial.service.users;


import java.util.List;
import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.FriendDetailDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.dto.UserFriendDTO;
import com.polysocial.dto.UserUpdateDTO;
import com.polysocial.dto.UserUpdatePasswordDTO;
import com.polysocial.dto.UsersDTO;
import com.polysocial.entity.Friends;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserFriendDTO getOneUser(Long userId, Long userByToken);

    FriendDTO getUserFriend(Long userId, Long friendId);
    
    List<FriendDetailDTO> getAllFriend(Long userId);

    List<UserDTO> searchUserByEmail(String email);

    List<UserDTO> searchUserByName(String name);

    FriendDetailDTO addFriend(Long userConfirmId, String studentCode);

    FriendDetailDTO acceptFriend(Long userConfirmId, Long userInviteId);

    void deleteRequestAddFriend(Long userConfirmId, Long userInviteId);

    List<FriendDetailDTO> getAllRequestAddFriend(Long userId);

    List<FriendDetailDTO> getAllRequestAddFriendByUserIntive(Long userId);

    List<UserFriendDTO> searchByKeyWord(String keyword, Long userId);

    List<UsersDTO> getAllUserNotStudent();

    void deleteFriend(Long userId, Long userFriendId);

    void resetPassword(String email);

    UserUpdateDTO updateProfile(UserUpdateDTO userUpdateDTO);

    UserUpdateDTO updatePassword(UserUpdatePasswordDTO userDTO);

    List<UserUpdateDTO> getAllUserFull();

}
