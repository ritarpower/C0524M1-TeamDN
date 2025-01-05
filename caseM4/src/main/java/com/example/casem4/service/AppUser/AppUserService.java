package com.example.casem4.service.AppUser;

import com.example.casem4.model.AppRole;
import com.example.casem4.model.AppUser;
import com.example.casem4.model.AppUserDTO;
import com.example.casem4.repository.IAppRoleRepository;
import com.example.casem4.repository.IAppUserRepository;
import com.example.casem4.service.AppUser.imple.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public boolean authenticateUser(String username, String password) {
        AppUser appUser = appUserRepository.findByUsername(username);
        return appUser != null && appUser.getPassword().equals(password);
    }


    @Override
    public boolean registerUser(AppUserDTO appUserDTO) {
        if (appUserRepository.findByUsername(appUserDTO.getUsername()) != null) {
            return false;
        }
        AppRole defaultRole = appRoleRepository.findByRole("USER");
        if (defaultRole == null) {
            return false;
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPhone(Long.valueOf(appUserDTO.getPhone()));
        appUser.setRole(defaultRole);
        appUser.setAddress(appUserDTO.getAddress());
        appUserRepository.save(appUser);
        return true;
    }

    public boolean checkUserByUsername(String username) {
        return appUserRepository.findByUsername(username) != null;
    }



    // Phương thức reset mật khẩu
    @Override
    public boolean resetPassword(String email) {
        // Tìm kiếm người dùng bằng email
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser != null) {
            // Gửi email để reset mật khẩu (giả sử bạn sẽ sử dụng một thư viện để gửi email)
            // Ví dụ: sử dụng JavaMail để gửi email reset mật khẩu
            // Hoặc có thể tạo mã xác nhận để người dùng đặt lại mật khẩu của mình
            return true; // Trả về true nếu gửi email thành công
        }
        return false; // Nếu không tìm thấy email trong hệ thống
    }

    // Phương thức kiểm tra email đã tồn tại trong hệ thống (đã có trong yêu cầu)
    public boolean checkUserByEmail(String email) {
        return appUserRepository.findByEmail(email) != null; // Kiểm tra xem email đã tồn tại chưa
    }
}
